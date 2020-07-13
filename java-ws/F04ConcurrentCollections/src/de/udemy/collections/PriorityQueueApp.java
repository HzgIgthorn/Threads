package de.udemy.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class PriorityWorker1 implements Runnable {

	private BlockingQueue<String> queue;

	public PriorityWorker1(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			queue.put("B");
			queue.put("H");
			queue.put("F");
			Thread.sleep(2100);
			queue.put("A");
			Thread.sleep(1000);
			queue.put("E");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class PriorityWorker2 implements Runnable {

	private BlockingQueue<String> queue;

	public PriorityWorker2(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
			System.out.println(queue.take());
			Thread.sleep(1000);
			System.out.println(queue.take());
			Thread.sleep(1000);
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class PriorityQueueApp {

	public static void main(String[] args) {
		//Die PriorityBlockingQueue gibt die Elemente mit einer sortierten Reihenfolge wieder heraus
		//Die PriorityBlockingQueue ist synchronisiert, sodass sich verschiedene Threads nicht
		//behinder können. Die zu sortierenden Elemente müssen dazu das Comparable-Interface implementiert haben
		BlockingQueue<String> queue = new PriorityBlockingQueue<>();
		new Thread(new PriorityWorker1(queue)).start();
		new Thread(new PriorityWorker2(queue)).start();
	}
}