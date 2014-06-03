package comanche.fractal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHandler implements IFileHandler {
	public Response handleRequest (Request r) throws IOException {
		File f = new File(r.url);
		if (f.exists() && !f.isDirectory()) {
			Response res = new Response();
			InputStream is = new FileInputStream(f);
			byte[] data = new byte[is.available()];
			is.read(data);
			res.data = data;
			is.close();
			res.message = "HTTP/1.0 200 OK\n\n";
			return res;
		} else {
			throw new IOException("File not found");
		}
	}
}