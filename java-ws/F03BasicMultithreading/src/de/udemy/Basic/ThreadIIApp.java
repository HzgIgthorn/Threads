package de.udemy.Basic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class Runner5 extends Thread{

	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(this.getClass().getSimpleName()+":"+i);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Runner6 extends Thread{
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(this.getClass().getSimpleName()+":"+i);
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class ThreadIIApp {
	
	static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");
	
	public static void main(String[] args) {
		
		Date before = new Date();
		System.out.println(ThreadIIApp.class.getSimpleName()+" starts at "+STF.format(before));
		
		Runner5 r5 = new Runner5();
		Runner6 r6 = new Runner6();
		
		r5.start();
		r6.start();
		
		try {
			r5.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(ThreadIIApp.class.getSimpleName()+" has finished in "
				+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}