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

public class Analyzer extends OpenCOMComponent implements IAnalyzer, IUnknown, ILifeCycle, IMetaInterface, IConnections {
	public OCM_SingleReceptacle<IDispatcher> id;
	public OCM_SingleReceptacle<ILogger> l;
	
	public Analyzer(IUnknown mpIOCM) {
		super(mpIOCM);
		this.id = new OCM_SingleReceptacle<IDispatcher>(IDispatcher.class);
		this.l = new OCM_SingleReceptacle<ILogger>(ILogger.class);
	}
	
	// functional aspect
	public Response handleRequest (Request r) throws IOException {		
		String rq = r.rq;
		l.m_pIntf.log(rq);
		if (rq.startsWith("GET ")) {
			r.url = rq.substring(5, rq.indexOf(' ', 4));
			return id.m_pIntf.handleRequest(r);
		}
		else { return null; }
	}

	public boolean startup(Object data) {
		return true;
	}

	public boolean shutdown() {
		return true;
	}

	public boolean connect(IUnknown pSinkIntf, String riid, long provConnID) {		
		if (riid.equals("comanche.opencom.IDispatcher")){			
			return this.id.connectToRecp(pSinkIntf, riid, provConnID);
		}
		else if (riid.equals("comanche.opencom.ILogger")) {
			return this.l.connectToRecp(pSinkIntf, riid, provConnID);
		}
		else {
			return false;			
		}
	}

	public boolean disconnect(String riid, long connID) {
		if (riid.equals("comanche.opencom.IDispatcher")){
			return this.id.disconnectFromRecp(connID);
		}
		else if (riid.equals("comanche.opencom.ILogger")) {
			return this.l.disconnectFromRecp(connID);
		}
		else
			return false;
	}
}
