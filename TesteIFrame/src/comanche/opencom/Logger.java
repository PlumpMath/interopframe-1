package comanche.opencom;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

public class Logger extends OpenCOMComponent implements ILogger, IUnknown, ILifeCycle, IMetaInterface {
	public Logger(IUnknown mpIOCM) {
		super(mpIOCM);		
	}

	public void log (String msg) { System.out.println(msg); }

	@Override
	public boolean shutdown() {		
		return true;
	}

	@Override
	public boolean startup(Object arg0) {		
		return true;
	}
}