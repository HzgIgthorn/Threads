package de.udemy.executorapi;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;

public class CallableTaskD implements Callable<TaskResult<String,Integer>>, Constants {
	private static int count = 0;
	private Date before;

	private int a;
	private int b;
	private long sleepTime;
	
	private int instanceNumber;
	private String taskId;
	
	public CallableTaskD(int a, int b, long sleepTime) {
		this.before = new Date();
		
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		
		this.instanceNumber = ++count;
		this.taskId = "ReturnBApp-"+instanceNumber;
		System.out.println("ReturnBApp starts at "+STF.format(before));
	}

	@Override
	public TaskResult<String,Integer> call() {
		String currentName = Thread.currentThread().getName();
		System.out.println("##### ["+currentName+"] < " +taskId+"> Sleeping for " + sleepTime + " millies");
		
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(taskId+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
		return new TaskResult<>(taskId,a+b);
	}
}