package de.udemy.threadapi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThreadAliveApp {
	
	public static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("AliveApp starts at "+STF.format(before));
		
		Thread t1 = new Thread(new LoopTaskStay(), "LoopThread-1");
		Thread t2 = new Thread(new LoopTaskStay(), "LoopThread-2");
		
		System.out.println("t1 is Alive->" +t1.isAlive()+" and t2 is alive->"+t2.isAlive());
		
		t1.start();
		t2.start();
		
		while(true) {
			try {
				TimeUnit.MILLISECONDS.sleep(600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Die isAlive() Methode überprüft, ob der Thread fertig ist
			System.out.println("t1 is Alive->" +t1.isAlive()+" and t2 is alive->"+t2.isAlive());
			if(!t1.isAlive() && !t2.isAlive())
				break;
		}
		System.out.println("AliveApp has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}