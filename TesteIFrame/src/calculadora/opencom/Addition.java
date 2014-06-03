package calculadora.opencom;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

public class Addition extends OpenCOMComponent implements IAddition, IUnknown, ILifeCycle, IMetaInterface {

	public Addition(IUnknown binder) {
		super(binder);		
	}

	public int add(int x, int y) {
		return (x+y);
	}

	public boolean startup(Object data) {
		return true;
	}

	public boolean shutdown() {
		return true;
	}
	
}
