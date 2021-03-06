package de.udemy.threadapi;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FifthWay implements Constants{

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("Main thread starts at "+STF.format(before));

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Date before = new Date();
				System.out.println("<Runnable> starts at "+STF.format(before));
				for(int i = 10; i > 0; i-- ) {
					System.out.println("<Runnable> TICK TICK " + i);
					try {
						TimeUnit.MILLISECONDS.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("<Runnable> has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
			}
		}).start();
		System.out.println("Main thread has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}
