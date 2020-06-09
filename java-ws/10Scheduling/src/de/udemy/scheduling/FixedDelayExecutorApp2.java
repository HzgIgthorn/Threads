package de.udemy.scheduling;

import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class FixedDelayExecutorApp2 implements Constants{

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		ScheduledExecutorService execServ = Executors.newSingleThreadScheduledExecutor(new NamedThreadsFactory("Fixed-Delay-Thread"));
		//Der Executor führt nach 4 sek das erste Mal den Task aus, dieser dauert 1 sek
		//Nachdem der Task fertig ist, wird er 2 sek nach der Fertigstellung continuierlich wiederholt
		ScheduledFuture<?> schedFut1 = execServ.scheduleWithFixedDelay(new OneTimeTask(1000), 4, 2, TimeUnit.SECONDS);
		
		try {
			for(int i = 0 ; i < 10; i++) {
				LocalTime schedDate = LocalTime.now().plusNanos(schedFut1.getDelay(TimeUnit.NANOSECONDS));
				System.out.println("Thread "+thread+"scheduled at approx. "+DTF.format(schedDate));
				TimeUnit.MILLISECONDS.sleep(1500);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		execServ.shutdown();
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}