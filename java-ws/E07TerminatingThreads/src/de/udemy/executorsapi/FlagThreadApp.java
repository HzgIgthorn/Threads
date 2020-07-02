package de.udemy.executorsapi;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.LoopFlagTask;

public class FlagThreadApp implements Constants{

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		ExecutorService execServ = Executors.newCachedThreadPool(new NamedThreadsFactory("Faktor-Thread"));
		
		try(LoopFlagTask task1 = new LoopFlagTask();
				LoopFacTask task2 = new LoopFacTask(30L, 1000L)) {
			execServ.execute(task1);
			execServ.submit(task2);
			execServ.shutdown();
			TimeUnit.MILLISECONDS.sleep(3000);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}