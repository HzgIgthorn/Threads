package de.udemy.JoinThreads;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class CountDownTask implements Runnable, Constants {
	
	public static final String name = "Task";
	public String thread;

	private static int count = 0;
	private int id;
	Date before;
	long time;
	
	CountDownLatch cdl;
	public static int FAK = 1000;
	
	public CountDownTask() {
		super();
		before = new Date();
		thread = Thread.currentThread().getName();
		this.id = ++count;
		System.out.println(thread+" " +name+id+" starts at "+STF.format(before));
	}
	
	public CountDownTask(long time, CountDownLatch cdl) {
		super();
		before = new Date();
		this.cdl = cdl;
		thread = Thread.currentThread().getName();
		this.id = ++count;
		this.time = time;
		System.out.println(thread+" " +name+id+" starts at "+STF.format(before));
	}

	@Override
	public void run() {
		for(int i = 10; i > 0; i-- ) {
			thread = Thread.currentThread().getName();
			System.out.println("<"+thread+"> TICK TICK " + i);
			
			try {
				TimeUnit.MILLISECONDS.sleep((long)((time > 0)?time:(FAK * RAND.nextDouble())));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(cdl != null)
			cdl.countDown();
		System.out.println("the latch count is " + cdl.getCount());
		System.out.println(thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}