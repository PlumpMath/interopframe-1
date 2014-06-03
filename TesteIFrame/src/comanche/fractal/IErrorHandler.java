package comanche.fractal;

public interface IErrorHandler {
  Response handleRequest (Request r) throws java.io.IOException;
}
