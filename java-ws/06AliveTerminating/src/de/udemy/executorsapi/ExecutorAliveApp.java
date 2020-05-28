package de.udemy.executorsapi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ExecutorAliveApp {
	
	public static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("AliveApp starts at "+STF.format(before));
		
		ExecutorService execServ = Executors.newCachedThreadPool(new NamedThreadsFactory("AliveThread-"));
		Future<?> f1 = execServ.submit(new LoopTaskA());
		Future<Integer> f2 = execServ.submit(new CallableTaskB(3, 4, 700));
		
		//FutureTask ist eine konkrete Implementierung con 
		FutureTask<?> f3 = new FutureTask<Void>(new LoopTaskA(), null);
		FutureTask<Integer> f4 = new FutureTask<Integer>(new CallableTaskB(3, 4, 700));
		
		execServ.execute(f3);
		execServ.execute(f4);
		
		execServ.shutdown();
		
		for(int i = 0; i < 5; i++) {
			try {
				TimeUnit.MILLISECONDS.sleep(600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//die isDone() Methode prüft, ob das Future den Wert erhalten hat.
			System.out.println("f1 is Alive-> " +f1.isDone()+" and f2 is alive-> "+f2.isDone());
			System.out.println("f3 is Alive-> " +f3.isDone()+" and f4 is alive-> "+f4.isDone());
		}
		
		try {
			System.out.println("Result of f1 is: " +f1.get());
			System.out.println("Result of f2 is: " +f2.get());
			System.out.println("Result of f3 is: " +f3.get());
			System.out.println("Result of f4 is: " +f4.get());
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("AliveApp has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}