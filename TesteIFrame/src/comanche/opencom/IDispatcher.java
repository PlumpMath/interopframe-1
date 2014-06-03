package comanche.opencom;

import OpenCOM.IUnknown;

public interface IDispatcher extends IUnknown {
  comanche.fractal.Response handleRequest (comanche.fractal.Request r) throws java.io.IOException;
}