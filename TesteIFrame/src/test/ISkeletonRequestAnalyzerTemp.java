package test;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISkeletonRequestAnalyzerTemp extends Remote {
	public comanche.fractal.Response handleRequest(comanche.fractal.Request p0) throws RemoteException, java.io.IOException;
}