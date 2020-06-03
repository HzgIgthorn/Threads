package de.udemy.executorsapi;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.LoopFlagTask;
import de.udemy.threadapi.LoopInterruptTask;

public class InterruptExecutorApp implements Constants{

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
			Future<Long> f3 = execServ.submit(task3);
			
			TimeUnit.MILLISECONDS.sleep(3000);
			execServ.shutdownNow();
			
			System.out.println("Future1 " + f1.get()+", Future2 "+f2.get()+" und Future3 " + f3.get());

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}