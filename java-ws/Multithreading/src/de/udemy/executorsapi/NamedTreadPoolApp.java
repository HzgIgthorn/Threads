package de.udemy.executorsapi;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;
import de.udemy.threadapi.LoopTaskStay;

public class NamedTreadPoolApp implements Constants {

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println(thread+" starts at "+STF.format(before));
		
		ExecutorService execServ = Executors.newCachedThreadPool();
		ExecutorService execNameed = Executors.newCachedThreadPool(new NamedThreadsFactory("NamedThread"));
		
		execServ.execute(new LoopTaskStay());
		execServ.execute(new LoopTaskStay());
		execServ.execute(new LoopTaskStay());
		
		execServ.shutdown();
		
		execNameed.execute(new LoopTaskStay());
		execNameed.execute(new LoopTaskStay());
		
		try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Zwei Threads wurden schon erzeugt und werden jetzt einfach recycelt. Zusätzlich müssen 3 neue
		//Threads erzeugt werden, die dann auch einen neuen Namen bekommen.
		execNameed.execute(new LoopTaskStay());
		execNameed.execute(new LoopTaskStay());
		execNameed.execute(new LoopTaskStay());
		execNameed.execute(new LoopTaskStay());
		execNameed.execute(new LoopTaskStay());
		
		execNameed.shutdown();
		
		System.out.println(thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}