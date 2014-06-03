package calculadora.opencom;

import OpenCOM.IConnections;
import OpenCOM.ILifeCycle;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;
import OpenCOM.OCM_SingleReceptacle;
import OpenCOM.OpenCOMComponent;

public class Multiplication extends OpenCOMComponent implements IMultiplication, IUnknown, ILifeCycle, IMetaInterface, IConnections {

	public OCM_SingleReceptacle<IAddition> rcAddition;
	
	public Multiplication(IUnknown binder) {
		super(binder);		
		this.rcAddition = new OCM_SingleReceptacle<IAddition>(IAddition.class);
	}
	
	// Interface IMultiplier implementation
	public int multiply(int x, int y) {
		int signal = 1;
		int abs_x = Math.abs(x);
		int abs_y = Math.abs(y);
		int result = abs_y;
		if (((x>=0)&&(y<0)) || ((x<0)&&(y>=0))) {
			signal = -1;
		}
		for (int i=1; i<abs_x; i++) {
			result = this.rcAddition.m_pIntf.add(result, abs_y);
		}
		return (result/signal);
	}
	// ------------------------------------
	
	// ILifeCycle Interface Implementation
	public boolean startup(Object data) {
		return true;
	}
	
	public boolean shutdown() {
		return true;
	}
	// -----------------------------------
	
	// IConnections Interface
	public boolean connect(IUnknown compToConnect, String riid, long provConnID) {
		if (riid.equals("calculadora.opencom.IAddition")) {
			return this.rcAddition.connectToRecp(compToConnect, riid, provConnID);
		}
		return false;
	}

	public boolean disconnect(String riid, long connID) {
		if (riid.equals("calculadora.opencom.IAddition")) {
			return this.rcAddition.disconnectFromRecp(connID);
		} 
		return false;
	}
	// ----------------------
		
}
