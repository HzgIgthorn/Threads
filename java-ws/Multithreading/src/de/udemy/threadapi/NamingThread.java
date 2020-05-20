package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NamingThread implements Constants{

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		Date before = new Date();
		System.out.println("Thread "+thread+" starts at "+STF.format(before));
		
		new Thread(new LoopTaskName()).start();
		//Der Name des Threads kann als zweites Argument übergeben werden
		Thread t2 = new Thread(new LoopTaskStay(), "Thread 2");
		t2.start();
		Thread t3 = new Thread(new LoopTaskStay());
		//Wir können den Namen des Threads ändern, nachdem er erzeugt wurde. Der Name des Threads kann auch
		//noch geändert werden, wenn er schon läuft.
		t3.setName("Thread 3");
		t3.start();
		
		System.out.println("Thread "+thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}