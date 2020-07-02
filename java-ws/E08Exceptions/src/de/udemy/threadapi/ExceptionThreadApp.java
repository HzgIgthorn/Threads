package de.udemy.threadapi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ExceptionThreadApp {
	
	static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		Runnable task1 = new ExceptionTask();
		Runnable task2 = new ExceptionTask();
		Runnable task3 = new ExceptionTask();
		
		//Der ExceptionHandler fängt die Exceptions der Threads ab
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("DEFAULT_HANDLER"));
		new Thread(task1, "Thread-1").start();
		new Thread(task2, "Thread-2").start();
		new Thread(task3, "Thread-3").start();
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}