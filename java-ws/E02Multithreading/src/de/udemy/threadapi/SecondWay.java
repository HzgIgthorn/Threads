package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SecondWay implements Constants{

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("Main thread starts at "+STF.format(before));
		new SecondTask().start();
		Thread t2 = new SecondTask();
		t2.start();
		System.out.println("Main thread has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}

class SecondTask extends Thread implements Constants{
	
	private static int count = 0;
	private int id;
	
	public SecondTask() {
		this.id = ++count;
	}

	@Override
	public void run() {
		Date before = new Date();
		System.out.println("T"+id+" starts at "+STF.format(before));
		for(int i = 10; i > 0; i-- ) {
			System.out.println("<T"+id+"> TICK TICK " + i);
			
			try {
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("T"+id+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}
