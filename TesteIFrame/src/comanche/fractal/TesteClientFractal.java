package comanche.fractal;

import interopframe.api.IFrame;
import interopframe.core.InteropFrame;

public class TesteClientFractal {

	public static void main(String[] args) {

		//Runtime do Fractal Client
		Receiver rr = new Receiver();
		IScheduler s = new Scheduler();
		rr.bindFc("s", s);	

		//Runtime do InteropFrame lado Cliente
		IFrame iFrame = new InteropFrame("169.254.197.31", 6666);
		iFrame.setParameters("comanche.fractal.Receiver",
				"comanche.fractal.IAnalyzer", "Fractal",
				"comanche.fractal.Analyzer",
				"comanche.fractal.IAnalyzer", "Fractal");
		iFrame.setParametersBinding("WebServiceSOAP", "169.254.197.31");
		iFrame.remoteBinding(rr);

		rr.run();
	}
}
