package test;

import OpenCOM.ILifeCycle;
import OpenCOM.IOpenCOM;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOM;

public class TesteMainServer {
	public static void main(String[] args) {
//		IFrame iFrame = new InteropFrame();		
//		
//		Parameters parameters = new Parameters("test.Calculadora", "test.IFactorialClient", "Fractal", 
//												"test.Factorial", "test.IFactorial", "OpenCOM");
//		ParametersBinding pBinding = new ParametersBinding("RMI");
//		new ServerPadraoRMI();
		
		OpenCOM runtime = new OpenCOM();
		IOpenCOM ocm = (IOpenCOM) runtime.QueryInterface("OpenCOM.IOpenCOM");
		
		IUnknown unkFactorial = (IUnknown) ocm.createInstance("test.Factorial", "Factorial");
		ILifeCycle lcFactorial = (ILifeCycle) unkFactorial.QueryInterface("OpenCOM.ILifeCycle");
		lcFactorial.startup(ocm);		
	}
}
