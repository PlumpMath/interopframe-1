package calculadora.opencom;

import interopframe.core.InteropFrame;
import interopframe.utils.ServerPadraoRMI;
import OpenCOM.ILifeCycle;
import OpenCOM.IOpenCOM;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOM;

public class ServerCalculatorOpenCOM {

	public static void main(String[] args) {
		
		long ini = System.currentTimeMillis();
		
		OpenCOM runtime = new OpenCOM();
		IOpenCOM ocm = (IOpenCOM) runtime.QueryInterface("OpenCOM.IOpenCOM");

		IUnknown addUnk = (IUnknown) ocm.createInstance("calculadora.opencom.Addition", "addition");
		ILifeCycle addLife = (ILifeCycle) addUnk.QueryInterface("OpenCOM.ILifeCycle");
		addLife.startup(ocm);

		/*IUnknown subUnk = (IUnknown) ocm.createInstance("calculadora.opencom.Subtraction", "subtraction");
		ILifeCycle subLife = (ILifeCycle) subUnk.QueryInterface("OpenCOM.ILifeCycle");
		subLife.startup(ocm);

		IUnknown multUnk = (IUnknown) ocm.createInstance("calculadora.opencom.Multiplication", "multiplication");
		ILifeCycle multLife = (ILifeCycle) multUnk.QueryInterface("OpenCOM.ILifeCycle");
		multLife.startup(ocm);*/

		/*IUnknown factUnk = (IUnknown) ocm.createInstance("calculadora.opencom.Factorial", "factorial");
		ILifeCycle factLife = (ILifeCycle) factUnk.QueryInterface("OpenCOM.ILifeCycle");
		factLife.startup(ocm);

		IUnknown powUnk = (IUnknown) ocm.createInstance("calculadora.opencom.Power", "power");
		ILifeCycle powLife = (ILifeCycle) powUnk.QueryInterface("OpenCOM.ILifeCycle");
		powLife.startup(ocm);*/

		IUnknown calcUnk = (IUnknown) ocm.createInstance("calculadora.opencom.Calculator", "calculator");
		ILifeCycle calcLife = (ILifeCycle) calcUnk.QueryInterface("OpenCOM.ILifeCycle");
		calcLife.startup(ocm);

		//ocm.connect(multUnk, addUnk, "calculadora.opencom.IAddition");
		ocm.connect(calcUnk, addUnk, "calculadora.opencom.IAddition");		
		//ocm.connect(calcUnk, subUnk, "calculadora.opencom.ISubtraction");
		//ocm.connect(calcUnk, multUnk, "calculadora.opencom.IMultiplication");		
		//ocm.connect(calcUnk, factUnk, "calculadora.opencom.IFactorial");		
		//ocm.connect(calcUnk, powUnk, "calculadora.opencom.IPower");
		
		new ServerPadraoRMI();
		new InteropFrame(ocm);		
		
		System.out.println("Server Ok...");
		
		long end = System.currentTimeMillis();
		System.out.println("Tempo de carregamento: "+ (end-ini));
		ICalculator c = (ICalculator) calcUnk.QueryInterface("calculadora.opencom.ICalculator");
		System.out.println("c.add(7,3) = "+c.add(7,3));
		System.out.println("c.add(7,3) = "+c.add2(7,3));
		System.out.println("c.add(7,3) = "+c.add3(7,3));
		System.out.println("c.add(7,3) = "+c.add4(7,3));
		System.out.println("c.add(7,3) = "+c.add5(7,3));
		
		//System.exit(0);
		
		/*ICalculator c = (ICalculator) calcUnk.QueryInterface("calculadora.opencom.ICalculator");
						
		System.out.println("c.add(7,3) = "+c.add(7,3));
		System.out.println("c.subtract(7,3) = "+c.subtract(7,3));
		System.out.println("c.multiply(7,3) = "+c.multiply(7,3));
		System.out.println("c.factorial(3) = "+c.factorial(3));
		System.out.println("c.power(2.0,3.0) = "+c.power(2.0,3.0));
		
		
		ocm.disconnect(connID_Mult_Add);
		ocm.disconnect(connID_Calc_Add);
		ocm.disconnect(connID_Calc_Sub);
		ocm.disconnect(connID_Calc_Mult);
		ocm.disconnect(connID_Calc_Fact);
		ocm.disconnect(connID_Calc_Pow);
		
		
		ocm.deleteInstance(addUnk);
		ocm.deleteInstance(subUnk);
		ocm.deleteInstance(multUnk);
		ocm.deleteInstance(factUnk);
		ocm.deleteInstance(powUnk);
		ocm.deleteInstance(calcUnk);
		*/
	}

}
