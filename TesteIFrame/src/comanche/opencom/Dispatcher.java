package comanche.opencom;

import java.io.IOException;

import OpenCOM.IConnections;
import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OCM_SingleReceptacle;
import OpenCOM.OpenCOMComponent;

import comanche.fractal.Request;
import comanche.fractal.Response;

public class Dispatcher extends OpenCOMComponent implements IDispatcher, IUnknown, ILifeCycle, IMetaInterface, IConnections {
	public OCM_SingleReceptacle<IFileHandler> h0;
	public OCM_SingleReceptacle<IErrorHandler> h1;
	int count = 0;	
	
	public Dispatcher(IUnknown mpIOCM) {
		super(mpIOCM);
		this.h0 = new OCM_SingleReceptacle<IFileHandler>(IFileHandler.class);
		this.h1 = new OCM_SingleReceptacle<IErrorHandler>(IErrorHandler.class);		
	}
	
	// functional aspect
	public Response handleRequest (Request r) throws IOException {		
		Response res;		
		res = (this.h0.m_pIntf).handleRequest(r);
		if (res == null) {
			res = this.h1.m_pIntf.handleRequest(r);
		}
		return res;
	}

	@Override
	public boolean connect(IUnknown pSinkIntf, String riid, long provConnID) {		
		if (count == 0){			
			count++;
			return this.h0.connectToRecp(pSinkIntf, riid, provConnID);
		}
		if (count == 1) {			
			return this.h1.connectToRecp(pSinkIntf, riid, provConnID);
		}
		return false;
	}

	@Override
	public boolean disconnect(String riid, long connID) {	
		if (this.h0.disconnectFromRecp(connID) && this.h1.disconnectFromRecp(connID)) {
			return true;
		}
		return false;
		
	}

	public boolean startup(Object data) {
		return true;
	}

	public boolean shutdown() {
		return true;
	}
}