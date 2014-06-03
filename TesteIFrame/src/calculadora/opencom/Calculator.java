package calculadora.opencom;

import OpenCOM.OCM_SingleReceptacle;
import OpenCOM.OpenCOMComponent;
import OpenCOM.ILifeCycle;
import OpenCOM.IConnections;
import OpenCOM.IMetaInterface;
import OpenCOM.IUnknown;


public class Calculator extends OpenCOMComponent implements ICalculator, IUnknown, ILifeCycle, IMetaInterface, IConnections {

	public OCM_SingleReceptacle<IAddition> rcAdder;
	
	//public OCM_SingleReceptacle<ISubtraction> rcSubtract; 
	
	//public OCM_SingleReceptacle<IMultiplication> rcMultiplier; 

	//public OCM_SingleReceptacle<IFactorial> rcFactorial; 

	//public OCM_SingleReceptacle<IPower> rcPower; 

	public Calculator(IUnknown binder) {
		super(binder);
		this.rcAdder = new OCM_SingleReceptacle<IAddition>(IAddition.class); 
		//this.rcSubtract = new OCM_SingleReceptacle<ISubtraction>(ISubtraction.class);
		//this.rcMultiplier = new OCM_SingleReceptacle<IMultiplication>(IMultiplication.class);
		//this.rcFactorial = new OCM_SingleReceptacle<IFactorial>(IFactorial.class);
		//this.rcPower = new OCM_SingleReceptacle<IPower>(IPower.class);
	}
	
	public int add(int x, int y) {
		return this.rcAdder.m_pIntf.add(x,y);
	}
	
	public int add2(int x, int y) {
		return this.rcAdder.m_pIntf.add(x,y);
	}
	
	public int add3(int x, int y) {
		return this.rcAdder.m_pIntf.add(x,y);
	}
	
	public int add4(int x, int y) {
		return this.rcAdder.m_pIntf.add(x,y);
	}
	
	public int add5(int x, int y) {
		return this.rcAdder.m_pIntf.add(x,y);
	}

	/*public int subtract(int x, int y) {
		return this.rcSubtract.m_pIntf.subtract(x,y);
	}

	public int multiply(int x, int y) {
		return this.rcMultiplier.m_pIntf.multiply(x,y);
	}*/

	/*public int factorial(int x) {
		return this.rcFactorial.m_pIntf.factorial(x);
	}
	
	public double power(double x, double y) {
		return this.rcPower.m_pIntf.power(x, y);
	}*/

	public boolean startup(Object data) {
		return true;
	}

	public boolean shutdown() {
		return true;
	}

	public boolean connect(IUnknown pSinkIntf, String riid, long provConnID) {
		if (riid.equals("calculadora.opencom.IAddition")){
			return this.rcAdder.connectToRecp(pSinkIntf, riid, provConnID);
		} /*else if (riid.equals("calculadora.opencom.ISubtraction")){
			return this.rcSubtract.connectToRecp(pSinkIntf, riid, provConnID);
		} else if (riid.equals("calculadora.opencom.IMultiplication")){
			return this.rcMultiplier.connectToRecp(pSinkIntf, riid, provConnID);
		} else if (riid.equals("calculadora.opencom.IFactorial")){
			return this.rcFactorial.connectToRecp(pSinkIntf, riid, provConnID);
		} else if (riid.equals("calculadora.opencom.IPower")){
			return this.rcPower.connectToRecp(pSinkIntf, riid, provConnID);
		} */
		return false;
	}

	public boolean disconnect(String riid, long connID) {
		if (riid.equals("calculadora.opencom.IAddition")){
			return this.rcAdder.disconnectFromRecp(connID);
		} /*else if (riid.equals("calculadora.opencom.ISubtraction")){
			return this.rcSubtract.disconnectFromRecp(connID);
		} else if (riid.equals("calculadora.opencom.IMultiplication")){
			return this.rcMultiplier.disconnectFromRecp(connID);
		} else if (riid.equals("calculadora.opencom.IFactorial")){
			return this.rcFactorial.disconnectFromRecp(connID);
		} else if (riid.equals("calculadora.opencom.IPower")){
			return this.rcPower.disconnectFromRecp(connID);
		} */
		return false;
	}
	
}
