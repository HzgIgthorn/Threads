package de.udemy.collections;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CyclicWorker implements Runnable {

	private int id;
	private Random random;
	private CyclicBarrier barrier;

	public CyclicWorker(int id, CyclicBarrier barrier) {
		super();
		this.barrier = barrier;
		this.random = new Random();
		this.id = id;
	}

	@Override
	public void run() {
		doWork();

	}

	private void doWork() {
		System.out.println("Thread mit Id " + id + " staret...");
		try {
			Thread.sleep(random.nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("... Thread mit Id " + id + " ist fertig.");

		try {
			barrier.await();
			System.out.println("Thread mit Id " + id + " ist nach await.");
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Id=" + this.id;
	}
	
	
}

public class CyclicBarrierApp {
	
	private static final int MAX = 5;

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		CyclicBarrier barrier = new CyclicBarrier(MAX, new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Alle Tasks sind abgearbeitet.");
			}
		});
		
		for(int i = 0; i < MAX; i++) {
			service.execute(new CyclicWorker(i+1, barrier));
		}
		
		service.shutdown();
	}
}