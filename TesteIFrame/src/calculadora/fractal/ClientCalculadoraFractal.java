package calculadora.fractal;

import interopframe.api.IFrame;
import interopframe.core.InteropFrame;

public class ClientCalculadoraFractal {
	public static void main(String[] args) {

		Calculadora calc = new Calculadora();

		IFrame iFrame = new InteropFrame();
		iFrame.setParameters("calculadora.fractal.Calculadora", "calculadora.fractal.ICalculator", "Fractal",
				"calculadora.opencom.Calculator", "calculadora.opencom.ICalculator", "OpenCOM");
		iFrame.setParametersBinding("RMI");		
		iFrame.remoteBinding(calc);

		calc.run();
	}
}
