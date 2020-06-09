package de.udemy.scheduling;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


public class CalculationTask implements Callable<Integer>, Constants {
	private static int count = 0;
	private Date before;

	private int a;
	private int b;
	private long sleepTime;
	
	private int instanceNumber;
	private String taskId;
	
	public CalculationTask(int a, int b, long sleepTime) {
		this.before = new Date();
		
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		
		this.instanceNumber = ++count;
		this.taskId = this.getClass().getSimpleName()+instanceNumber;
		System.out.println(this.getClass().getSimpleName()+" starts at "+STF.format(before));
	}

	@Override
	public Integer call() {
		String currentName = Thread.currentThread().getName();
		System.out.println("##### ["+currentName+"] < " +taskId+"> Sleeping for " + sleepTime + " millies");
		
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(taskId+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
		return a+b;
	}
}