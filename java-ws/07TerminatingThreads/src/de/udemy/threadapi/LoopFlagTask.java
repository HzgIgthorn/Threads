package de.udemy.threadapi;

import java.io.Closeable;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;

public class LoopFlagTask implements Runnable, Constants, Closeable {
	
	public static final String name = "Task";
	public String thread;

	private static int count = 0;
	private int id;
	Date before;
	
	private volatile boolean isDown = false;
	
	public static int FAK = 3000;
	
	public LoopFlagTask() {
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
				e.printStackTrace();
			}
			synchronized(this) {
				if(isDown) {
					break;
				}
			}
		}
	}

	@Override
	public void close() throws IOException {
		synchronized(this) {
			this.isDown = true;
		}
		System.out.println(thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}