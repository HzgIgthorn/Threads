package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FourthWay implements Constants{

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("Main thread starts at "+STF.format(before));
		new Thread(new FourthTask()).start();
		Runnable r2 = new FourthTask();
		new Thread(r2).start();
		System.out.println("Main thread has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}

class FourthTask implements Constants,Runnable{
	
	private static int count = 0;
	private int id;
	
	public FourthTask() {
		this.id = ++count;
	}

	@Override
	public void run() {
		Date before = new Date();
		System.out.println("R"+id+" starts at "+STF.format(before));
		for(int i = 10; i > 0; i-- ) {
			System.out.println("<R"+id+"> TICK TICK " + i);
			
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("R"+id+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}
