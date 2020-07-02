package de.udemy.scheduling;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class OneTimeApp implements Constants{

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		//Es kann auch ein newScheduledthreadPool() mit mehreren Thread verwendet werden
		ScheduledExecutorService execServ = Executors.newSingleThreadScheduledExecutor(new NamedThreadsFactory("One-Time-Thread"));
		System.out.println("["+thread+"] current time: "+STF.format(new Date()));
		
		ScheduledFuture<?> schedFut1 = execServ.schedule(new OneTimeTask(0), 6, TimeUnit.SECONDS);
		ScheduledFuture<Integer> schedFut2 = execServ.schedule(new CalculationTask(2, 3, 0), 4, TimeUnit.SECONDS);
		execServ.schedule(new OneTimeTask(0), 8, TimeUnit.SECONDS);
		ScheduledFuture<Integer> schedFut4 = execServ.schedule(new CalculationTask(3, 4, 0), 10, TimeUnit.SECONDS);
		
		execServ.shutdown();
		
		//Wir können einen Task mit cancel() abbrechen, während der Thread den Task gerade bearbeitet
		schedFut1.cancel(true);
		schedFut2.cancel(true);
		System.out.println("["+thread+"] retrieving the results");
		try {
			System.out.println("["+thread+"] task-1 result: "+schedFut1.get());
			System.out.println("["+thread+"] task-2 result: "+schedFut2.get());
			System.out.println("["+thread+"] task-4 result: "+schedFut4.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}