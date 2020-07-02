package de.udemy.Basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ExecWork implements Runnable{

	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.print(i+" ");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class ExecutorApp {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i < 5; i++) {
			//Die execute Methode kann Runnable an den ExecutorService schicken
			service.execute(new ExecWork());
		}
		System.out.println("");
	}
}