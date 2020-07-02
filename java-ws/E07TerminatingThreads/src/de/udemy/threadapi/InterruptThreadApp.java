package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class InterruptThreadApp implements Constants{

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		LoopInterruptTask task1 = new LoopInterruptTask();
		LoopInterruptTask task2 = new LoopInterruptTask();
		LoopInterruptTask task3 = new LoopInterruptTask();
		
		Thread t1 = new Thread(task1, "InterThread-1");
		Thread t2 = new Thread(task2, "InterThread-2");
		Thread t3 = new Thread(task3, "InterThread-3");
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			TimeUnit.MILLISECONDS.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("["+thread+"] interrupting " + t1.getName());
		t1.interrupt();
		
		System.out.println("["+thread+"] interrupting " + t2.getName());
		t2.interrupt();
		
		System.out.println("["+thread+"] interrupting " + t3.getName());
		t3.interrupt();
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}