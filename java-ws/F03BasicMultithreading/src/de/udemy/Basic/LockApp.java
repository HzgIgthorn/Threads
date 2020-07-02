package de.udemy.Basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockApp {

	private static int counter1 = 0;
	private static int counter2 = 0;
	
	//Wir können eine Methode auch über ein Lock syncronisieren. Der Vorteil ist, dass
	//die unlock Methode auch in einer anderen Methode aufgerufen werden kann.
	private static Lock lock = new ReentrantLock();
	
	public static void increment1() {
		for(int i=0; i < 10000; i++) {
			counter1++;
		}
	}
	
	public static void increment2() {
		
		lock.lock();
		try {
			for(int i=0; i < 10000; i++) {
				counter2++;
			}
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				increment1();
				increment2();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				increment1();
				increment2();
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
		
		System.out.println("Counter1 ist bei: "+counter1+" und Counter2 ist bei "+counter2);
	}
}