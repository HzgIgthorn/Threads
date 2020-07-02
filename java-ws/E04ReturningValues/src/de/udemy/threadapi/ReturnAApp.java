package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReturnAApp implements Constants{

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("ReturnAApp starts at "+STF.format(before));
		
		ReturnTaskA task1 = new ReturnTaskA(2, 3, 1000);
		Thread t1 = new Thread(task1, "Thread-1");
		
		ReturnTaskA task2 = new ReturnTaskA(3, 4, 2000);
		Thread t2 = new Thread(task2, "Thread-2");
		
		ReturnTaskA task3 = new ReturnTaskA(4, 5, 500);
		Thread t3 = new Thread(task3, "Thread-3");
		
		t1.start();
		t2.start();
		t3.start();
		
		System.out.println("Result-1 = " + task1.getSum());
		System.out.println("Result-2 = " + task2.getSum());
		System.out.println("Result-3 = " + task3.getSum());
		
		System.out.println("Main thread has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}

}
