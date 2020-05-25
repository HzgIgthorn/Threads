package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReturnTaskA implements Runnable, Constants {
	private static int count = 0;
	private Date before;

	private int a;
	private int b;
	private long sleepTime;
	private int sum;
	
	private int instanceNumber;
	private String taskId;
	
	private volatile boolean notDone = true;
	
	public ReturnTaskA(int a, int b, long sleepTime) {
		this.before = new Date();
		
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		
		this.instanceNumber = ++count;
		this.taskId = "ReturnTaskA-"+instanceNumber;
		System.out.println("ReturnTaskA starts at "+STF.format(before));
	}

	@Override
	public void run() {
		String currentName = Thread.currentThread().getName();
		System.out.println("##### ["+currentName+"] < " +taskId+"> Sleeping for " + sleepTime + " millies");
		
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sum=a+b;
		notDone = false;
		//Es werden alle Threads informiert, dass der Thread fertig ist.
		synchronized(this) {
			System.out.println(taskId+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis -> notify all");
			this.notifyAll();
		}
		
	}

	public int getSum() {
		if(notDone) {
			//wenn der Thread noch nicht fertig ist (notDone = true), Dann wird der Main-Thread angehalten
			synchronized(this) {
				try {
					System.out.println("["+Thread.currentThread().getName()+"] is waiting for done form "+ taskId);
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("["+Thread.currentThread().getName()+"] is woken up by "+ taskId);
		}
		return sum;
	}
}
