package de.udemy.threadapi;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FlagThreadApp implements Constants{

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		try(LoopFlagTask task1 = new LoopFlagTask();
				LoopFlagTask task2 = new LoopFlagTask();
				LoopFlagTask task3 = new LoopFlagTask();) {
			new Thread(task1, "TermThread-1").start();
			new Thread(task2, "TermThread-2").start();
			new Thread(task3, "TermThread-3").start();
			TimeUnit.MILLISECONDS.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}