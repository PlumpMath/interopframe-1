package comanche.opencom;

import java.io.IOException;
import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

import comanche.fractal.Request;
import comanche.fractal.Response;

public class ErrorHandler extends OpenCOMComponent implements IErrorHandler, IUnknown, ILifeCycle, IMetaInterface {
	public ErrorHandler(IUnknown mpIOCM) {
		super(mpIOCM);		
	}

	public Response handleRequest (Request r) throws IOException {
		Response res = new Response();
		res.message = "HTTP/1.0 404 Not Found\n\n"+"<html>Document not found.</html>";
		res.data = new byte[0];
		return res;    
	}

	@Override
	public boolean shutdown() {
		return true;
	}

	@Override
	public boolean startup(Object arg0) {		
		return true;
	}
}