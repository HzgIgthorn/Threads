package de.udemy.executorsapi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.ThreadExceptionHandler;

public class ExceptionThreadApp {
	
	static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		//Exception Handler wird systemweit für alle Threads gesetzt. Jeder Thread im Executor nutzt
		//damit per default den gleichen Exception Handler
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("DEFAULT_HANDLER"));
		
		ExecutorService execServ1 = Executors.newCachedThreadPool();
		execServ1.execute(new ExceptionTask());
		execServ1.execute(new ExceptionTask());
		execServ1.execute(new ExceptionTask());
		
		//Die Factory fügt jedem Thread einen eigenen Exception Handler hinzu
		//damit wird der default Exception Handler ignoriert
		ExecutorService execServ2 = Executors.newCachedThreadPool(new HandlerThreadsFactory("HaThread"));
		execServ2.execute(new ExceptionTask());
		execServ2.execute(new ExceptionTask());
		execServ2.execute(new ExceptionTask());
		
		execServ1.shutdown();
		execServ2.shutdown();
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}