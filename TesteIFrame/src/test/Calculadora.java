package test;

import org.objectweb.fractal.api.control.BindingController;

import test.IFactorialClient;

public class Calculadora implements java.lang.Runnable, BindingController {

	private IFactorialClient ifact;

	public Calculadora () {
		System.err.println("CLIENTE CALCULADORA CRIADO");
	}

	public void run () {
		System.out.println(ifact.factorial(6));
	}

	public String[] listFc () {
		return new String[] { "fact" };
	}

	public Object lookupFc (final String cItf) {
		if (cItf.equals("fact")) {
			return ifact;
		}
		return null;
	}

	public void bindFc (final String cItf, final Object sItf) {
		if (cItf.equals("fact")) {
			ifact = (IFactorialClient)sItf;
		}
	}

	public void unbindFc (final String cItf) {
		if (cItf.equals("fact")) {
			ifact = null;
		}
	}
}
