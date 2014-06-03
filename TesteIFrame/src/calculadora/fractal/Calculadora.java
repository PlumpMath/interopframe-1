package calculadora.fractal;

import org.objectweb.fractal.api.control.BindingController;

public class Calculadora implements java.lang.Runnable, BindingController {

	private ICalculator ic;

	public Calculadora () {
		System.err.println("CLIENTE CALCULADORA CRIADO");
	}

	public void run () {		
		int a = 5;
		int b = 4;
		System.out.println("Soma: "+ic.add(a, b));
		System.out.println("Multiplicacao: "+ic.multiply(a, b));
		System.out.println("Subtracao: "+ic.subtract(a, b));
		System.out.println("Factorial: "+ic.factorial(a));
		System.out.println("Power: "+ic.power(a, b));
	}

	public String[] listFc () {
		return new String[] { "calc" };
	}

	public Object lookupFc (final String cItf) {
		if (cItf.equals("calc")) {
			return ic;
		}
		return null;
	}

	public void bindFc (final String cItf, final Object sItf) {
		if (cItf.equals("calc")) {
			ic = (ICalculator)sItf;
		}
	}

	public void unbindFc (final String cItf) {
		if (cItf.equals("calc")) {
			ic = null;
		}
	}
}
