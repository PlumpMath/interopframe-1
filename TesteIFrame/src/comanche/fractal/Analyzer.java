package comanche.fractal;

import java.io.IOException;
import org.objectweb.fractal.api.control.BindingController;

public class Analyzer implements IAnalyzer, BindingController {
	private IDispatcher id;
	private ILogger l;

	// configuration aspect
	public String[] listFc () { return new String[] { "l", "id" }; }
	
	public Object lookupFc (String itfName) {
		if (itfName.equals("l")) { return l; }
		else if (itfName.equals("id")) { return id; }
		else return null;
	}
	
	public void bindFc (String itfName, Object itfValue) {
		if (itfName.equals("l")) { l = (ILogger)itfValue; }
		else if (itfName.equals("id")) { id = (IDispatcher)itfValue; }
	}
	
	public void unbindFc (String itfName) {
		if (itfName.equals("l")) { l = null; }
		else if (itfName.equals("id")) { id = null; }
	}
	
	// functional aspect
	public Response handleRequest (Request r) throws IOException {
		String rq = r.rq;
		l.log(rq);
		if (rq.startsWith("GET ")) {
			r.url = rq.substring(5, rq.indexOf(' ', 4));
			return id.handleRequest(r);
		}
		else { return null; }
	}
}