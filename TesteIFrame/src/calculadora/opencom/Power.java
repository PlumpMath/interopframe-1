package calculadora.opencom;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

public class Power extends OpenCOMComponent implements IPower, IUnknown, ILifeCycle, IMetaInterface {

	public Power(IUnknown binder) {
		super(binder);		
	}

	public double power(double x, double y) {
       	return Math.pow(x, y);
	}

	public boolean startup(Object data) {
		return true;
	}

	public boolean shutdown() {
		return true;
	}
	
}
