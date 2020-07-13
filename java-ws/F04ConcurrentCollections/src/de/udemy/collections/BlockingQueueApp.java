package de.udemy.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class FirstWorker implements Runnable{

	private BlockingQueue<Integer> queue;
	
	public FirstWorker(BlockingQueue<Integer> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		int counter = 0;
		
		while(true) {
			try {
				queue.put(counter);
				System.out.println("Füge Int zu Warteschlange hinzu "+counter++);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class SecondWorker implements Runnable{

	private BlockingQueue<Integer> queue;
	
	public SecondWorker(BlockingQueue<Integer> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		
		while(true) {
			try {
				int number = queue.take();
				System.out.println("Nehme "+number+ " aus Warteschlange");
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class BlockingQueueApp {

	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
		
		FirstWorker first = new FirstWorker(queue);
		SecondWorker second = new SecondWorker(queue);
		
		new Thread(first).start();;
		new Thread(second).start();
	}
}