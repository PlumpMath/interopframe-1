package comanche.opencom;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

import OpenCOM.IConnections;
import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OCM_SingleReceptacle;
import OpenCOM.OpenCOMComponent;

import comanche.fractal.Request;
import comanche.fractal.Response;

public class Receiver extends OpenCOMComponent implements Runner, IUnknown, ILifeCycle, IMetaInterface, IConnections {
	public OCM_SingleReceptacle<IAnalyzer> ia;
	public OCM_SingleReceptacle<IScheduler> s;
	private ServerSocket ss;

	public Receiver(IUnknown mpIOCM) {
		super(mpIOCM);
		this.ia = new OCM_SingleReceptacle<IAnalyzer>(IAnalyzer.class);
		this.s = new OCM_SingleReceptacle<IScheduler>(IScheduler.class);
	}

	// functional aspect
	public void run () {
		try {
			ss = new ServerSocket(8080);
			System.out.println("Comanche HTTP Server ready on port 8080.");
			while (true) {
				final Socket socket = ss.accept();
				final Request r = new Request();
				s.m_pIntf.schedule(new  Runnable () {
					public void run () {
						try {
							Reader in = new InputStreamReader(socket.getInputStream());
							PrintStream out = new PrintStream(socket.getOutputStream());
							String rq = new LineNumberReader(in).readLine();
							r.rq = rq;
							Response res = ia.m_pIntf.handleRequest(r);
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

	public boolean connect(IUnknown pSinkIntf, String riid, long provConnID) {
		if (riid.equals("comanche.opencom.IAnalyzer")){
			return this.ia.connectToRecp(pSinkIntf, riid, provConnID);
		}
		else if (riid.equals("comanche.opencom.IScheduler")) {
			return this.s.connectToRecp(pSinkIntf, riid, provConnID);
		}
		else {
			return false;			
		}
	}

	public boolean disconnect(String riid, long connID) {
		if (riid.equals("comanche.opencom.IAnalyzer")){
			return this.ia.disconnectFromRecp(connID);
		}
		else if (riid.equals("comanche.opencom.IScheduler")) {
			return this.s.disconnectFromRecp(connID);
		}
		else
			return false;
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