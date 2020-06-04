package de.udemy.JoinThreads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownApp {
	
	static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		ExecutorService execServ = Executors.newCachedThreadPool(new NamedThreadsFactory("CountDownThread-"));
		//Die CountDownLatch zählt einfach die Threads, die das Signal gesendet haben, dass sie fertig sind
		CountDownLatch cdl = new CountDownLatch(4);
		
		execServ.execute(new CountDownTask(100,cdl));
		execServ.execute(new CountDownTask(200,cdl));
		execServ.execute(new CountDownTask(500,cdl));
		execServ.execute(new CountDownTask(300,cdl));
		
		execServ.shutdown();
		
		try {
			cdl.await();
			System.out.println("["+thread+"] " + thread + " GOT SIGNAL... ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}