package de.udemy.executorsapi;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;

public class CachedTreadPoolApp implements Constants {

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("Main thread starts at "+STF.format(before));
		
		//Ein CachedThreadPool stellt immer so viele Threads zur Verfügung, wie gerade gebraucht werden
		//Wenn ein Thrad fertig ist, wartet er in einer Queue auf das nächste Runnable zum Abarbeiten
		ExecutorService execServ = Executors.newCachedThreadPool();
		
		execServ.execute(new LoopTaskA());
		execServ.execute(new LoopTaskA());
		execServ.execute(new LoopTaskA());
		
		execServ.execute(new LoopTaskA());
		execServ.execute(new LoopTaskA());
		
		//Der ExecutorService beendet nach Shutdown noch die vorhandenen Tasks, aber nimmt keine neuen mehr an.
		execServ.shutdown();
		
		System.out.println("Main thread has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}