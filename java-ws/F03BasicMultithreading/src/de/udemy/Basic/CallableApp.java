package de.udemy.Basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Processor implements Callable<String>{

	private int id;
	
	public Processor(int id) {
		super();
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "Id: "+id;
	}
}

public class CallableApp {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		List<Future<String>> list = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			//Die submit Methode kann Runnable und Callable zum ExecutorService schicken
			Future<String> future = service.submit(new Processor(i+1));
			list.add(future);
		}
		
		service.shutdown();
		
		for(Future<String> future: list) {
			try {
				System.out.print(future.get()+" ");
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}