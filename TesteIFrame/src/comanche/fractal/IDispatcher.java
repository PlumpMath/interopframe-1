package comanche.fractal;

public interface IDispatcher {
  Response handleRequest (Request r) throws java.io.IOException;
}
