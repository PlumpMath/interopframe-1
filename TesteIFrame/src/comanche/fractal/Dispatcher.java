package comanche.fractal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.objectweb.fractal.api.control.BindingController;

public class Dispatcher implements IDispatcher, BindingController {
	private Map<String, Object> handlers = new TreeMap<String, Object>();

	// configuration aspect
	public String[] listFc () {
		return (String[])handlers.keySet().toArray(new String[handlers.size()]);
	}
	
	public Object lookupFc (String itfName) {
		if (itfName.startsWith("h")) { return handlers.get(itfName); }
		else return null;
	}
	
	public void bindFc (String itfName, Object itfValue) {
		if (itfName.startsWith("h")) { handlers.put(itfName, itfValue); }
	}
	
	public void unbindFc (String itfName) {
		if (itfName.startsWith("h")) { handlers.remove(itfName); }
	}
	
	// functional aspect
	public Response handleRequest (Request r) throws IOException {
		Iterator<Object> i = handlers.values().iterator();
		Response res;
		while (i.hasNext()) {
			try {				 
				res = ((IFileHandler)i.next()).handleRequest(r);									
				return res;				
			} catch (IOException _) { 
				res = ((IErrorHandler)i.next()).handleRequest(r); 
				return res; 
			} 
		} return null;
	}
}