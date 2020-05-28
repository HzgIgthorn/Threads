package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;

public class LoopSleepTask implements Runnable, Constants {
	
	public static final String name = "Task";
	public String thread;

	private static int count = 0;
	private int id;
	Date before;
	
	public static int FAK = 3000;
	
	public LoopSleepTask() {
		super();
		before = new Date();
		thread = Thread.currentThread().getName();
		this.id = ++count;
		System.out.println(thread+" " +name+id+" starts at "+STF.format(before));
	}

	@Override
	public void run() {
		this.thread = Thread.currentThread().getName();
		for(int i = 1; true; i++ ) {
			
			System.out.println("<"+thread+"> TICK TICK " + i);
			
			try {
				TimeUnit.MILLISECONDS.sleep((long)(FAK * RAND.nextDouble()));
			} catch (InterruptedException e) {
				//Ein schlafender Thread der das Interrupt Flag bekommt wirft eine InterruptedException
				System.out.println("["+thread+"] "+name+id+" Sleep Interrupted. Cancelling...");
				break;
			}
		}
	}
}