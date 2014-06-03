package calculadora.opencom;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

public class Factorial extends OpenCOMComponent implements IFactorial, IUnknown, ILifeCycle, IMetaInterface {

	public Factorial(IUnknown binder) {
		super(binder);		
	}

	public int factorial(int x) {
		if (x<=1)  
			return 1;  
        else  
        	return x * factorial(x-1);
	}

	public boolean startup(Object data) {
		return true;
	}

	public boolean shutdown() {
		return true;
	}
	
}
