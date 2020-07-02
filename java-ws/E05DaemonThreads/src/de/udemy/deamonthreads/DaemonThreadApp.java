package de.udemy.deamonthreads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DaemonThreadApp {
	
	public static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("DeamonThreadApp starts at "+STF.format(before));
		
		Thread t1 = new Thread(new LoopTaskStay(500), "Thread-1");
		Thread t2 = new Thread(new LoopTaskStay(1000), "Thread-2");
		//t2 wird als Daemon-Thread definiert. Damit bricht er abbrupt ab, wenn alle normalen User-Threads fertig sind
		t2.setDaemon(true);
		
		t1.start();
		t2.start();

		System.out.println("ReturnDApp has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}

}
