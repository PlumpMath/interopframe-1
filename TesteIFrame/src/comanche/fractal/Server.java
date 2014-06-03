package comanche.fractal;

public class Server {
	public static void main (String[] args) {
		Receiver rr = new Receiver();
		Analyzer ra = new Analyzer();
		Dispatcher rd = new Dispatcher();
		FileHandler frh = new FileHandler();
		ErrorHandler erh = new ErrorHandler();
		IScheduler s = new Scheduler();
		ILogger l = new Logger();
		rr.bindFc("ia", ra);
		rr.bindFc("s", s);
		ra.bindFc("id", rd);
		ra.bindFc("l", l);
		rd.bindFc("h0", frh);
		rd.bindFc("h1", erh);
		rr.run();
	}
}