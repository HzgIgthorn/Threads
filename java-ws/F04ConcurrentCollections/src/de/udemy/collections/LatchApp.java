package de.udemy.collections;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LatchWorker implements Runnable{
	
	private int id;
	private CountDownLatch counter;
	
	public LatchWorker(int id, CountDownLatch counter) {
		super();
		this.id = id;
		this.counter = counter;
	}

	@Override
	public void run() {
		doWork();
		counter.countDown();
	}

	private void doWork() {
		System.out.println("Thread mit id "+ this.id + " startet...");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class LatchApp {
	
	private static final int MAX = 5;

	public static void main(String[] args) {
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		CountDownLatch latch = new CountDownLatch(MAX);
		for(int i = 0; i < MAX; i++) {
			service.execute(new LatchWorker(i, latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Alle LatchWorker sind fertig...");
		service.shutdown();
	}
}