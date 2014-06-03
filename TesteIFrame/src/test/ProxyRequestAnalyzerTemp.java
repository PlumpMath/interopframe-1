package test;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import comanche.fractal.IAnalyzer;
import test.ISkeletonRequestAnalyzerTemp;
import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OpenCOMComponent;
public class ProxyRequestAnalyzerTemp extends OpenCOMComponent implements IAnalyzer, IUnknown, ILifeCycle, IMetaInterface {
	ISkeletonRequestAnalyzerTemp stub;
	public ProxyRequestAnalyzerTemp(IUnknown binder) {
		super(binder);
		startStub();
	}        
	public boolean startup(Object data) {
		return true;
	}	
	public boolean shutdown() {
		return true;
	}	
	public comanche.fractal.Response handleRequest(comanche.fractal.Request p0) throws java.io.IOException {
		comanche.fractal.Response resultado = null;
		try {
			resultado = stub.handleRequest(p0);
		} catch (RemoteException re) {
			re.printStackTrace();
		}
		return resultado;
	}
	public void startStub() {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 6767);
			this.stub = (ISkeletonRequestAnalyzerTemp) registry.lookup("SkeletonRequestAnalyzer");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
}