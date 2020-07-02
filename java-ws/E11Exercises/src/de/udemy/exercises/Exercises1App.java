package de.udemy.exercises;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercises1App implements Constants{
	
	private final static int NUM = 1000;

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		
		Map<String, Long> testMap = new HashMap<>();
		
		for(int n = 1; n < 20; n++) {
			Date before = new Date();
			System.out.println("Thread "+thread+" starts at "+STF.format(before));

			
			ExecutorService execServ = Executors.newFixedThreadPool(n);
			CountDownLatch cdl = new CountDownLatch(NUM);
			
			for(int i = 0; i < NUM; i++) {
				LoopFacTask task = new LoopFacTask(100L, 0L, cdl);
				execServ.submit(task);
			}
			
			execServ.shutdown();
			
			try {
				cdl.await();
				System.out.println("["+thread+"] " + thread + " GOT SIGNAL... ");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long time = TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS);
			testMap.put(""+n, time);
			
			System.out.println("Thread "+thread+" has finished in "+
					time+"  millis");
		}
		ExecutorService execServ = Executors.newCachedThreadPool();
		CountDownLatch cdl = new CountDownLatch(NUM);
		
		for(int i = 0; i < NUM; i++) {
			LoopFacTask task = new LoopFacTask(100L, 0L, cdl);
			execServ.submit(task);
		}
		
		execServ.shutdown();
		
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		try {
			cdl.await();
			System.out.println("["+thread+"] " + thread + " GOT SIGNAL... ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS);
		testMap.put("Chached", time);
		
		System.out.println("Thread "+thread+" has finished in "+
				time+"  millis");
		sysprin(testMap, "# of threads");
	}

	private static void sysprin(Map<String, Long> testMap, String name) {
		StringBuilder builder = new StringBuilder("====================================\r\n");
		   builder.append("  "+name+"          TIME TAKEN  \r\n");
		   builder.append("====================================\r\n");
		for(Entry<String,Long> entry : testMap.entrySet()) {
			builder.append("       "+entry.getKey()+"           <"+entry.getValue()+" millis>\r\n");
		}
		builder.append("====================================");
		System.out.println(builder.toString());
		
								   
		
	}
}