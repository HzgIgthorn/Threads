package de.udemy.scheduling;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class SchedulingTask extends TimerTask implements Constants {
	private static int count = 0;
	private Date before;

	private long sleepTime;
	
	private int instanceNumber;
	private String taskId;
	
	public SchedulingTask(long sleepTime) {
		this.before = new Date();
		this.sleepTime = sleepTime;
		
		this.instanceNumber = ++count;
		this.taskId = "ReturnTaskA-"+instanceNumber;
		System.out.println("SchedulingTask starts at "+STF.format(before));
	}

	@Override
	public void run() {
		String currentName = Thread.currentThread().getName();
		Date scheDate = new Date(super.scheduledExecutionTime());
		System.out.println("##### ["+currentName+"] < " +taskId+"> Scheduled to run at: " + STF.format(scheDate));
		
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(taskId+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}