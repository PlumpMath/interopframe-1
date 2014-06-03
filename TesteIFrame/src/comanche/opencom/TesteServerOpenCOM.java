package comanche.opencom;

import OpenCOM.ILifeCycle;
import OpenCOM.IOpenCOM;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOM;
import interopframe.core.InteropFrame;
import interopframe.utils.ServerPadraoRMI;

public class TesteServerOpenCOM {
	public static void main(String[] args) {
		long ini = System.currentTimeMillis();		
		
		//Runtime do OpenCOM Server
		OpenCOM runtime = new OpenCOM();
        IOpenCOM oc =  (IOpenCOM) runtime.QueryInterface("OpenCOM.IOpenCOM");
		
		IUnknown pRequestAnalyzerUnk = (IUnknown) oc.createInstance("comanche.opencom.Analyzer", "Analyzer");
        ILifeCycle pRequestAnalyzerLife =  (ILifeCycle) pRequestAnalyzerUnk.QueryInterface("OpenCOM.ILifeCycle");
        pRequestAnalyzerLife.startup(oc);
        
        IUnknown pRequestDispatcherUnk = (IUnknown) oc.createInstance("comanche.opencom.Dispatcher", "Dispatcher");
        ILifeCycle pRequestDispatcherLife =  (ILifeCycle) pRequestDispatcherUnk.QueryInterface("OpenCOM.ILifeCycle");
        pRequestDispatcherLife.startup(oc);
        
        IUnknown pFileRequestHandlerUnk = (IUnknown) oc.createInstance("comanche.opencom.FileHandler", "FileHandler");
        ILifeCycle pFileRequestHandlerLife =  (ILifeCycle) pFileRequestHandlerUnk.QueryInterface("OpenCOM.ILifeCycle");
        pFileRequestHandlerLife.startup(oc);
        
        IUnknown pErrorRequestHandlerUnk = (IUnknown) oc.createInstance("comanche.opencom.ErrorHandler", "ErrorHandler");
        ILifeCycle pErrorRequestHandlerLife =  (ILifeCycle) pErrorRequestHandlerUnk.QueryInterface("OpenCOM.ILifeCycle");
        pErrorRequestHandlerLife.startup(oc);
        
        IUnknown pLoggerUnk = (IUnknown) oc.createInstance("comanche.opencom.Logger", "Logger");
        ILifeCycle pLoggerLife =  (ILifeCycle) pLoggerUnk.QueryInterface("OpenCOM.ILifeCycle");
        pLoggerLife.startup(oc);
        
        oc.connect(pRequestAnalyzerUnk, pRequestDispatcherUnk, "comanche.opencom.IDispatcher");
        oc.connect(pRequestAnalyzerUnk, pLoggerUnk, "comanche.opencom.ILogger");
        oc.connect(pRequestDispatcherUnk, pFileRequestHandlerUnk, "comanche.opencom.IFileHandler");
        oc.connect(pRequestDispatcherUnk, pErrorRequestHandlerUnk, "comanche.opencom.IErrorHandler");
        
        //Runtime do InteropFrame lado Servidor
        new InteropFrame(oc);		
		new ServerPadraoRMI();        
		
		long end = System.currentTimeMillis();
		System.out.println("Tempo de carregamento: " + (end-ini) );
		
	    System.out.println("Server... Ok...");
	}
}
