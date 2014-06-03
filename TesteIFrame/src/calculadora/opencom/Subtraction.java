package calculadora.opencom;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

public class Subtraction extends OpenCOMComponent implements ISubtraction, IUnknown, ILifeCycle, IMetaInterface {

	public Subtraction(IUnknown binder) {
		super(binder);		
	}

	public int subtract(int x, int y) {
		return (x-y);
	}

	public boolean startup(Object data) {
		return true;
	}

	public boolean shutdown() {
		return true;
	}	

}
