package de.udemy.Basic;

public class SynchronizedBlockApp {
	
	private static int counter1 = 0;
	private static int counter2 = 0;
	
	//das Schlüsselwort synchronized synchronisiert immer auf einer ganzen Klasse. Wenn eine Methode
	//mit dem Schlüsselwort synchronized aufgerufen wird, kann keine andere Methode der Klasse mit dem
	//synchronized Schlüsselwort aufgerufen werden. Deshalb defnieren wir uns zwei Objecte, auf denen
	//wie synchronized aufrufen können. Dann wird nur über dieses Object synchroniesiert und alle Methoden
	//mit synchronized über diesem Object dürfen nicht gleichzeitig aufgerufen werden.
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

	public static void add() {
		synchronized (lock1) {
			counter1++;
		}
	}
	
	public synchronized static void addAgain() {
		synchronized (lock2) {
			counter2++;
		}
	}
	
	public static void compute() {
		for(int i =0; i < 100; i++) {
			add();
			addAgain();
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				compute();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				compute();
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
		
		System.out.println("Counter1 "+ counter1 +" und Counter2 "+ counter2 );
		
	}
}