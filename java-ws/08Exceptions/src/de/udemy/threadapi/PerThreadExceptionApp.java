package de.udemy.threadapi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PerThreadExceptionApp {
	
	static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		Runnable task1 = new ExceptionTask();
		Runnable task2 = new ExceptionTask();
		Runnable task3 = new ExceptionTask();
		Runnable task4 = new ExceptionTask();
		Runnable task5 = new ExceptionTask();
		
		//Der ExceptionHandler fängt die Exceptions der Threads ab
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("DEFAULT_HANDLER"));
		Thread t1 = new Thread(task1, "Thread-1");
		t1.setUncaughtExceptionHandler(new ThreadExceptionHandler("THREAD_1_HANDLER"));
		
		Thread t2 = new Thread(task2, "Thread-2");
		t2.setUncaughtExceptionHandler(new ThreadExceptionHandler("THREAD_2_HANDLER"));
		
		Thread t3 = new Thread(task3, "Thread-3");
		t3.setUncaughtExceptionHandler(new ThreadExceptionHandler());
		
		Thread t4 = new Thread(task4, "Thread-4");
		
		Thread t5 = new Thread(task5, "Thread-5");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}