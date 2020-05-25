package de.udemy.executorapi;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;
import de.udemy.threadapi.ReturnTaskA;

public class ReturnBApp implements Constants{

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("ReturnBApp starts at "+STF.format(before));
		
		ExecutorService execServ = Executors.newCachedThreadPool(new NamedThreadsFactory("Callable Thread"));
		Future<Integer> res1 = execServ.submit(new CallableTaskB(2, 3, 2000));
		Future<Integer> res2 = execServ.submit(new CallableTaskB(3, 4, 1000));
		Future<Integer> res3 = execServ.submit(new CallableTaskB(4, 5, 500));
		
		Future<?> res4 = execServ.submit(new ReturnTaskA(1,9,200));
		Future<Double> res5 = execServ.submit(new ReturnTaskA(2,7,500),1.0);
		
		execServ.shutdown();
		
		try {
			//Der ExecutorService sorgt dafür, dass das get() erst aufgerufen wird, wenn das Ergebnis da ist
			System.out.println("Result 1 = " + res1.get());
			System.out.println("Result 2 = " + res2.get());
			System.out.println("Result 3 = " + res3.get());
			System.out.println("Result 4 = " + res4.get());
			System.out.println("Result 5 = " + res5.get());
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ReturnBApp has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}

}
