package de.udemy.Basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Arbiter{
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void producer() throws InterruptedException{
		lock.lock();
		System.out.println("Producer Methode...");
		//Die await Methode lässt erst alle Threads vor, die in dem Status wait sind
		condition.await();
		System.out.println("Producer again...");
		lock.unlock();
	}
	
	public void consumer() throws InterruptedException{
		lock.lock();
		Thread.sleep(2000);
		System.out.println("Consumer Methode...");
		//Die signal Methode signalisiert dem Thread, der await aufgerufen hat, dass er fertig ist.
		condition.signal();
		lock.unlock();
	}
}


public class Lock2App {
	
	public static void main(String[] args) {
		
		Arbiter arbiter = new Arbiter();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					arbiter.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					arbiter.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}