package comanche.opencom;

import interopframe.api.IFrame;
import interopframe.core.InteropFrame;
import OpenCOM.ILifeCycle;
import OpenCOM.IOpenCOM;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOM;

public class TesteClientOpenCOM {
	public static void main(String[] args) throws Exception {	
		
		//Runtime do OpenCOM Client
		OpenCOM runtime = new OpenCOM();
        IOpenCOM oc =  (IOpenCOM) runtime.QueryInterface("OpenCOM.IOpenCOM"); 
       
        IUnknown pRequestReceiverUnk = (IUnknown) oc.createInstance("comanche.opencom.Receiver", "Receiver");
        ILifeCycle pRequestReceiverLife =  (ILifeCycle) pRequestReceiverUnk.QueryInterface("OpenCOM.ILifeCycle");
        pRequestReceiverLife.startup(oc);
        
        IUnknown pSchedulerUnk = (IUnknown) oc.createInstance("comanche.opencom.Scheduler", "Scheduler");
        ILifeCycle pSchedulerLife =  (ILifeCycle) pSchedulerUnk.QueryInterface("OpenCOM.ILifeCycle");
        pSchedulerLife.startup(oc);
        
        oc.connect(pRequestReceiverUnk, pSchedulerUnk, "comanche.opencom.IScheduler");
        
        //Runtime do InteropFrame lado Cliente
        IFrame iFrame = new InteropFrame();
        iFrame.setParameters("comanche.opencom.Receiver",
							"comanche.opencom.IAnalyzer", "OpenCOM",
							"comanche.fractal.Analyzer",
							"comanche.fractal.IAnalyzer", "Fractal");
        iFrame.setParametersBinding("RMI");
        iFrame.remoteBinding(oc);
		
		Runner r = (Runner) pRequestReceiverUnk.QueryInterface("comanche.opencom.Runner");
        r.run();
	}
}