package comanche.opencom;

import OpenCOM.IUnknown;

public interface ILogger extends IUnknown {
	void log (String msg);
}