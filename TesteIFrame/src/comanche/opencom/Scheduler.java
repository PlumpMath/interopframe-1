package comanche.opencom;

import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;

public class Scheduler extends OpenCOMComponent implements IScheduler, IUnknown, ILifeCycle, IMetaInterface {
	public Scheduler(IUnknown mpIOCM) {
		super(mpIOCM);
	}

	public void schedule (Runnable task) { new Thread(task).start(); }

	@Override
	public boolean shutdown() {		
		return true;
	}

	@Override
	public boolean startup(Object arg0) {		
		return true;
	}
}