package de.udemy.scheduling;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class OneTimeTask implements Constants, Runnable {
	private static int count = 0;
	private Date before;

	private long sleepTime;
	
	private int instanceNumber;
	private String taskId;
	
	public OneTimeTask(long sleepTime) {
		this.before = new Date();
		this.sleepTime = sleepTime;
		
		this.instanceNumber = ++count;
		this.taskId = this.getClass().getSimpleName()+"-"+instanceNumber;
		System.out.println(this.getClass().getSimpleName()+" starts at "+STF.format(before));
	}

	@Override
	public void run() {
		String currentName = Thread.currentThread().getName();
		System.out.println("##### ["+currentName+"] < " +taskId+"> Scheduled to run at: " + STF.format(new Date()));
		
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(taskId+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}