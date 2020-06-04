package de.udemy.JoinThreads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JoinApp {
	
	static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		Thread t1 = new Thread(new LoopTaskStay(100), "Thread-1");
		Thread t2 = new Thread(new LoopTaskStay(500), "Thread-2");
		Thread t3 = new Thread(new LoopTaskStay(300), "Thread-3");
		
		ReturnTaskA r1 = new ReturnTaskA(1, 2, 100);
		Thread h1 = new Thread(r1, "tHread-1");
		ReturnTaskA r2 = new ReturnTaskA(3, 6, 200);
		Thread h2 = new Thread(r2, "tHread-2");
		ReturnTaskA r3 = new ReturnTaskA(6, 4, 500);
		Thread h3 = new Thread(r3, "tHread-3");
		
		t1.start();
		t2.start();
		t3.start();
		
		h1.start();
		h2.start();
		h3.start();
		
		join(h1,thread);
		System.out.println("Result-1 = "+r1.getSum());
		join(h2,thread);
		System.out.println("Result-2 = "+r2.getSum());
		join(h3,thread);
		System.out.println("Result-3 = "+r3.getSum());
		
		join(t1,thread);
		join(t2,thread);
		join(t3,thread);
		
		join(h1,thread);
		System.out.println("Result-1 = "+r1.getSum());
		join(h2,thread);
		System.out.println("Result-2 = "+r2.getSum());
		join(h3,thread);
		System.out.println("Result-3 = "+r3.getSum());
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
	
	private static void join(Thread t, String thread) {
		try {
			t.join();
			System.out.println("["+thread+"] '"+ thread + "'joined'" 
					+t.getName()+"'");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}