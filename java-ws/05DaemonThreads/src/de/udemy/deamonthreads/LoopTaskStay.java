package de.udemy.deamonthreads;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LoopTaskStay implements Runnable, Constants {
	
	public static final String name = "Task";
	public String thread;

	private static int count = 0;
	private int id;
	Date before;
	private long sleepTime;
	
	public static int FAK = 1000;
	
	public LoopTaskStay(long sleepTime) {
		super();
		before = new Date();
		thread = Thread.currentThread().getName();
		this.id = ++count;
		this.sleepTime = sleepTime;
		System.out.println(thread+" " +name+id+" starts at "+STF.format(before));
		
	}

	@Override
	public void run() {
		String threadTyp = (Thread.currentThread().isDaemon())?"DAEMON":"USER";
		
		for(int i = 10; i > 0; i-- ) {
			thread = Thread.currentThread().getName();
			System.out.println("<"+thread+"["+threadTyp+"]> TICK TICK " + i);
			
			try {
				if(sleepTime == 0)
					TimeUnit.MILLISECONDS.sleep((long)(FAK * RAND.nextDouble()));
				else
					TimeUnit.MILLISECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("<"+thread+"["+threadTyp+"]> has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}