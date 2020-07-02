package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;

public class LoopTaskH implements Runnable, Constants {
	
	public static final String name = "Task";
	public String thread;

	private static int count = 0;
	private int id;
	Date before;
	
	boolean isDown = false;
	
	public static int FAK = 3000;
	
	public LoopTaskH() {
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
				isDown = true;
			}
			doSomeMoreWork();
			if(isDown || Thread.interrupted()) {
				System.out.println(thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
				break;
			}
		}
	}

	private void doSomeMoreWork() {
		System.out.println("["+thread+"] "+name+id+" is doing some more work");
		
	}
}