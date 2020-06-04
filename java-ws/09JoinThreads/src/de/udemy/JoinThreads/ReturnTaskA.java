package de.udemy.JoinThreads;

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
	}

	public int getSum() {
		return sum;
	}
}