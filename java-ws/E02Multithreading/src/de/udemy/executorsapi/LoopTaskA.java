package de.udemy.executorsapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;

public class LoopTaskA implements Runnable, Constants {
	
	public static final String name = "Task";

	private static int count = 0;
	private int id;
	Date before;
	
	public static int FAK = 1000;
	
	public LoopTaskA() {
		super();
		before = new Date();
		System.out.println(name+id+" starts at "+STF.format(before));
		this.id = ++count;
	}

	@Override
	public void run() {
		for(int i = 10; i > 0; i-- ) {
			System.out.println("<"+name+id+"> TICK TICK " + i);
			
			try {
				TimeUnit.MILLISECONDS.sleep((long)(FAK * RAND.nextDouble()));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(name+id+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}

}
