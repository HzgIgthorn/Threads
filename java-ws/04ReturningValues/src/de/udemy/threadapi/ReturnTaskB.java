package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReturnTaskB implements Runnable, Constants {
	private static int count = 0;
	private Date before;

	private int a;
	private int b;
	private long sleepTime;
	private int sum;
	
	private int instanceNumber;
	private String taskId;
	
	ResultListener<Integer> listener;
	
	public ReturnTaskB(int a, int b, long sleepTime, ResultListener<Integer> listener) {
		this.before = new Date();
		
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		this.listener = listener;
		
		this.instanceNumber = ++count;
		this.taskId = "ReturnBApp-"+instanceNumber;
		System.out.println("ReturnBApp starts at "+STF.format(before));
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
		listener.notifyResult(this.sum);
		System.out.println(taskId+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}