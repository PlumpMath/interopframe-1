package comanche.fractal;

import interopframe.core.InteropFrame;
import interopframe.utils.ServerPadraoRMI;

public class TesteServerFractal {

	public static void main(String[] args) {
		
		//Runtime do Fractal Server
		Analyzer ra = new Analyzer();
		Dispatcher rd = new Dispatcher();
		FileHandler frh = new FileHandler();
		ErrorHandler erh = new ErrorHandler();
		ILogger l = new Logger();
		
		ra.bindFc("id", rd);
		ra.bindFc("l", l);
		rd.bindFc("h0", frh);
		rd.bindFc("h1", erh);
		
		//Runtime do InteropFrame lado Servidor
		new ServerPadraoRMI();
		new InteropFrame(ra);

		System.out.println("Server... Ok...");
	}
}