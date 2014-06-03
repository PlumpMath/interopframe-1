package comanche.fractal;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import org.objectweb.fractal.api.control.BindingController;

public class Receiver implements Runnable, BindingController {
	private IScheduler s;
	private IAnalyzer ia;
	private ServerSocket ss;
	
	// configuration aspect
	public String[] listFc () { return new String[] { "s", "ia" }; }
	
	public Object lookupFc (String itfName) {
		if (itfName.equals("s")) { return s; }
		else if (itfName.equals("ia")) { return ia; }
		else return null;
	}
	
	public void bindFc (String itfName, Object itfValue) {
		if (itfName.equals("s")) { s = (IScheduler)itfValue; }
		else if (itfName.equals("ia")) { ia = (IAnalyzer)itfValue; }
	}
	
	public void unbindFc (String itfName) {
		if (itfName.equals("s")) { s = null; }
		else if (itfName.equals("ia")) { ia = null; }
	}
	
	// functional aspect
	public void run () {
		try {
			ss = new ServerSocket(8080);
			System.out.println("Comanche HTTP Server ready on port 8080.");
			while (true) {
				final Socket socket = ss.accept();
				final Request r = new Request();        
				s.schedule(new  Runnable () {
					public void run () {
						try {
							Reader in = new InputStreamReader(socket.getInputStream());
							PrintStream out = new PrintStream(socket.getOutputStream());
							String rq = new LineNumberReader(in).readLine();
							r.rq = rq;
							Response res = ia.handleRequest(r);
							out.print(res.message);
							out.write(res.data);
							out.close();
							socket.close();              
						} catch (IOException _) { }
					}
				});
			}

		} catch (IOException e) { e.printStackTrace(); } 
	}
}