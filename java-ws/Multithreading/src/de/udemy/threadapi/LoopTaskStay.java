package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;

public class LoopTaskStay implements Runnable, Constants {
	
	public static final String name = "Task";
	public String thread;

	private static int count = 0;
	private int id;
	Date before;
	
	public static int FAK = 1000;
	
	public LoopTaskStay() {
		super();
		before = new Date();
		thread = Thread.currentThread().getName();
		this.id = ++count;
		System.out.println(thread+" " +name+id+" starts at "+STF.format(before));
		
	}

	@Override
	public void run() {
		for(int i = 10; i > 0; i-- ) {
			thread = Thread.currentThread().getName();
			System.out.println("<"+thread+"> TICK TICK " + i);
			
			try {
				TimeUnit.MILLISECONDS.sleep((long)(FAK * RAND.nextDouble()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}