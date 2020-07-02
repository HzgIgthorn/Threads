package de.udemy.deamonthreads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DaemonExecutorApp {
	
	public static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("DeamonThreadApp starts at "+STF.format(before));
		
		ExecutorService execServ = Executors.newCachedThreadPool(new DaemonThreadsFactory("ExecThread"));
		
		execServ.execute(new LoopTaskStay(700));
		execServ.execute(new LoopTaskStay(1000));
		
		execServ.execute(new LoopTaskStay(250));
		execServ.execute(new LoopTaskStay(500));
		
		execServ.shutdown();

		System.out.println("ReturnDApp has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}

}
