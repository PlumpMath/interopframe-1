package comanche.opencom;

import interopframe.api.Parameters;
import interopframe.api.ParametersBinding;
import interopframe.core.BindingGeneratorRMI;
import interopframe.core.CodeGeneratorOpenCOM;
import interopframe.core.IBindingGenerator;

public class TestCodeGeneratorOpenCOMClientFractalServer {
	
	public static void main(String[] args) {
		
		long ini = System.currentTimeMillis();
		Parameters parameters = new Parameters("comanche.opencom.RequestReceiver", "comanche.opencom.RequestHandler", "OpenCOM", 
												"comanche.fractal.RequestAnalyzer", "comanche.fractal.RequestHandler", "Fractal");
		ParametersBinding pBinding = new ParametersBinding("RMI", "localhost");
		
		IBindingGenerator bind = new BindingGeneratorRMI(parameters, pBinding);
		CodeGeneratorOpenCOM co = new CodeGeneratorOpenCOM(parameters, bind);
		
		
		
		co.compileProxy();
		//co.compileSkeleton();
		
		long end = System.currentTimeMillis();
		
		System.out.println(end-ini);
	}
}

