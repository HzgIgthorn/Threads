package de.udemy.executorsapi;

import java.io.Closeable;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;

public class LoopFacTask implements Callable<Long>, Constants, Closeable {
	
	public static final String name = "Task";
	public String thread;

	private static int count = 0;
	private int id;
	Date before;
	
	private long a;
	private long sleepTime;
	
	private volatile boolean isDown = false;
	
	public static int FAK = 3000;
	
	public LoopFacTask(Long a, Long sleepTime) {
		super();
		before = new Date();
		thread = Thread.currentThread().getName();
		this.id = ++count;
		this.a = a;
		this.sleepTime = sleepTime;
		System.out.println(thread+" " +name+id+" starts at "+STF.format(before));
	}

	@Override
	public Long call() {
		this.thread = Thread.currentThread().getName();
		long res = 1L;
		
		for(long i = 1; i <= a; i++ ) {
			res *= i;
			System.out.println("<"+thread+"> in iteration " + i + " intermediate result = " + res);
			
			try {
				TimeUnit.MILLISECONDS.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			synchronized(this) {
				if(isDown) {
					res=-1L;
					break;
				}
			}
		}
		return res;
	}

	@Override
	public void close() throws IOException {
		synchronized(this) {
			this.isDown = true;
		}
		System.out.println(thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}