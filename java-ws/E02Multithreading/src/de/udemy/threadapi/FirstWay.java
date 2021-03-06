package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FirstWay implements Constants{

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("Main thread starts at "+STF.format(before));
		new FirstTask();
		new FirstTask();
		System.out.println("Main thread has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}

class FirstTask extends Thread implements Constants{
	
	private static int count = 0;
	private int id;
	
	public FirstTask() {
		this.id = ++count;
		this.start();
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
