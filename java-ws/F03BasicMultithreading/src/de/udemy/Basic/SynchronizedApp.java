package de.udemy.Basic;

public class SynchronizedApp {
	
	private static int counterAsyc =0;
	private static int counterSync =0;
	
	public static synchronized void increment() {
		++counterSync;
	}
	
	static void process() {
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 100; i++)
					++counterAsyc;
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 100; i++)
					++counterAsyc;
			}
		});
		
		Thread t3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 100; i++)
					increment();
			}
		});
		
		Thread t4 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 100; i++)
					increment();
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		process();
		
		System.out.println(counterAsyc);
		System.out.println(counterSync);

	}

}
