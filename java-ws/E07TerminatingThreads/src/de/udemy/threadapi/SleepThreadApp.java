package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SleepThreadApp implements Constants{

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		LoopSleepTask task1 = new LoopSleepTask();
		LoopSleepTask task2 = new LoopSleepTask();
		LoopSleepTask task3 = new LoopSleepTask();
		
		Thread t1 = new Thread(task1, "InterThread-1");
		Thread t2 = new Thread(task2, "InterThread-2");
		Thread t3 = new Thread(task3, "InterThread-3");
		
		LoopTaskH task4 = new LoopTaskH();
		LoopTaskH task5 = new LoopTaskH();
		
		Thread t4 = new Thread(task4, "InterThread-4");
		Thread t5 = new Thread(task5, "InterThread-4");
		
		t1.start();
		t2.start();
		t3.start();
		
		t4.start();
		t5.start();
		
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
		
		System.out.println("["+thread+"] interrupting " + t4.getName());
		t4.interrupt();
		
		System.out.println("["+thread+"] interrupting " + t5.getName());
		t5.interrupt();
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}