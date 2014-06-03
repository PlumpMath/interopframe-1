package comanche.fractal;

import java.io.IOException;

public class ErrorHandler implements IErrorHandler {
	public Response handleRequest (Request r) throws IOException {
		Response res = new Response();
		res.message = "HTTP/1.0 404 Not Found\n\n"+"<html>Document not found.</html>";
		res.data = new byte[0];
		return res;    
	}
}