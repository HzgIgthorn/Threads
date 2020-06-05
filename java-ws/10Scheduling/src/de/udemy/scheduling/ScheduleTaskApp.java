package de.udemy.scheduling;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class ScheduleTaskApp implements Constants{

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		//Timer kann einen Thread zu einer bestimmten Uhrzeit starten. Der Timer hat nur einen
		//Thread, der alle Tasks ausführt.
		Timer timer = new Timer("Timer-Thread", true );
		LocalTime scheTime = LocalTime.now().plusSeconds(5);
		Instant instant = scheTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
		SchedulingTask task1 = new SchedulingTask(8000);
		//Der Timer für den SchedulingTask wir auf 5sek in der Zukunft gesetzt
		timer.schedule(task1, Date.from(instant));
		
		long delay = 10000;
		SchedulingTask task2 = new SchedulingTask(5000);
		//Der Timer kann auch einfach einen SchedulingTask 10sek in der Zukunft ohne Date-Objekt starten
		timer.schedule(task2, delay);
		
		delay = 20000;
		SchedulingTask task3 = new SchedulingTask(20000);
		//Der Timer kann auch einfach einen SchedulingTask 10sek in der Zukunft ohne Date-Objekt starten
		timer.schedule(task3, delay);
		
		try {
			TimeUnit.MILLISECONDS.sleep(32000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Der Timer wird gecancelt. Deshalb werden auch alle Threads vom Timer gestoppt
		timer.cancel();
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
	
	private static void join(Thread t, String thread) {
		try {
			t.join();
			System.out.println("["+thread+"] '"+ thread + "'joined'" 
					+t.getName()+"'");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}