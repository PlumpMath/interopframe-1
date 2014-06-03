package comanche.opencom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

import comanche.fractal.Request;
import comanche.fractal.Response;

public class FileHandler extends OpenCOMComponent implements IFileHandler, IUnknown, ILifeCycle, IMetaInterface {
	public FileHandler(IUnknown mpIOCM) {
		super(mpIOCM);		
	}

	public Response handleRequest (Request r) throws IOException {
		File f = new File(r.url);
		if (f.exists() && !f.isDirectory()) {
			Response res = new Response();	
			InputStream is = new FileInputStream(f);
			byte[] data = new byte[is.available()];
			is.read(data);
			res.data = data;
			is.close();
			res.message = "HTTP/1.0 200 OK\n\n";
			return res;      
		} else {			
			return null;
		}
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