package de.udemy.executorsapi;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.LoopFlagTask;
import de.udemy.threadapi.LoopInterruptTask;

public class InterruptThreadApp implements Constants{

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		ExecutorService execServ = Executors.newCachedThreadPool(new NamedThreadsFactory("Faktor-Thread"));
		
		try {
			LoopFlagTask task1 = new LoopFlagTask();
			LoopFacTaskB task3 = new LoopFacTaskB(30L, 1000L);
			LoopInterruptTask task2 = new LoopInterruptTask();
			Future<?> f1 = execServ.submit(task1);
			Future<?> f2 = execServ.submit(task2);
			Future<?> f3 = execServ.submit(task3);
			execServ.shutdown();
			TimeUnit.MILLISECONDS.sleep(3000);
			f1.cancel(true);
			f2.cancel(true);
			f3.cancel(true);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}