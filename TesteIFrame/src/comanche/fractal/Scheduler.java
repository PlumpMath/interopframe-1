package comanche.fractal;

public class Scheduler implements IScheduler {
	public void schedule (Runnable task) { new Thread(task).start(); }
}