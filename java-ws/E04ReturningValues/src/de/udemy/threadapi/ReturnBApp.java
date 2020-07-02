package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReturnBApp implements Constants{

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("ReturnBApp starts at "+STF.format(before));
		
		ReturnTaskB task1 = new ReturnTaskB(2, 3, 1000, new SumObserver("Task-1"));
		Thread t1 = new Thread(task1, "Thread-1");
		
		ReturnTaskB task2 = new ReturnTaskB(3, 4, 2000, new SumObserver("Task-2"));
		Thread t2 = new Thread(task2, "Thread-2");
		
		ReturnTaskB task3 = new ReturnTaskB(4, 5, 500, new SumObserver("Task-3"));
		Thread t3 = new Thread(task3, "Thread-3");
		
		t1.start();
		t2.start();
		t3.start();
		
		System.out.println("ReturnBApp has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}

}
