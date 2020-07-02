package de.udemy.scheduling;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class FixedDelayExecutorApp implements Constants{

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		ScheduledExecutorService execServ = Executors.newScheduledThreadPool(3, new NamedThreadsFactory("Fixed-Delay-Thread"));
		//Der Executor führt nach 4 sek das erste Mal den Task aus, dieser dauert 1 sek
		//Nachdem der Task fertig ist, wird er alle 2 sek (nach dem Start) wiederholt
		ScheduledFuture<?> schedFut1 = execServ.scheduleAtFixedRate(new OneTimeTask(1000), 4, 2, TimeUnit.SECONDS);
		ScheduledFuture<?> schedFut2 = execServ.scheduleAtFixedRate(new OneTimeTask(1000), 4, 2, TimeUnit.SECONDS);
		
		try {

			TimeUnit.MILLISECONDS.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		execServ.shutdown();
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}