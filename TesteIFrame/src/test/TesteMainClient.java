package test;

import org.objectweb.fractal.api.Component;
import org.objectweb.fractal.api.factory.GenericFactory;
import org.objectweb.fractal.api.type.ComponentType;
import org.objectweb.fractal.api.type.InterfaceType;
import org.objectweb.fractal.api.type.TypeFactory;
import org.objectweb.fractal.util.Fractal;

public class TesteMainClient {
	public static void main(String[] args) throws Exception {
				
		Component boot2 = Fractal.getBootstrapComponent();
		TypeFactory tf2 = Fractal.getTypeFactory(boot2);

		ComponentType rType2 = tf2.createFcType(new InterfaceType[] {
				tf2.createFcItfType("root", "java.lang.Runnable", false, false, true)
		});
		ComponentType calcClientType = tf2.createFcType(new InterfaceType[] {
				tf2.createFcItfType("root1", "java.lang.Runnable", false, false, false),					
				tf2.createFcItfType("fact", "test.IFactorialClient", true, false, false)
		});		
		
		GenericFactory cf2 = Fractal.getGenericFactory(boot2);

		Component rComp2 = cf2.newFcInstance(rType2, "composite", null);			
		Component calcClientComp = cf2.newFcInstance(calcClientType, "primitive", "test.Calculadora");			
		
		Fractal.getContentController(rComp2).addFcSubComponent(calcClientComp);
		Fractal.getBindingController(rComp2).bindFc("root", calcClientComp.getFcInterface("root1"));			
		
		((java.lang.Runnable)rComp2.getFcInterface("root")).run();
		
		IFactorialClient ifact = (IFactorialClient) calcClientComp.getFcInterface("fact");
		System.out.println(ifact.factorial(5));
	}
}
