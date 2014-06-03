package comanche.fractal;

public interface IFileHandler {
  Response handleRequest (Request r) throws java.io.IOException;
}
