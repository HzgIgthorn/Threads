package de.udemy.executorapi;

import java.util.Date;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;
import de.udemy.threadapi.ReturnTaskA;

public class ReturnCApp implements Constants{

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("ReturnBApp starts at "+STF.format(before));
		
		ExecutorService execServ = Executors.newCachedThreadPool(new NamedThreadsFactory("Callable Thread"));
		
		CompletionService<Integer> tasks = new ExecutorCompletionService<>(execServ);
		
		tasks.submit(new CallableTaskB(2, 3, 2000));
		tasks.submit(new CallableTaskB(3, 4, 1000));
		tasks.submit(new CallableTaskB(4, 5, 500));
		
		tasks.submit(new ReturnTaskA(1,9,200),-1);
		tasks.submit(new ReturnTaskA(2,7,500),-1);
		
		execServ.shutdown();
		
		for(int i  = 0; i < 5; i++) {
			try {
				//Der Completion Service gibt bei jedem take() ein Future zurück, das den Rückgabewert der Callable darstellt
				//Wenn aktuelle kein Callabel fertig geworden ist, wird der aktuelle Thread angehalten, bis ein Callable fertig ist
				System.out.println("Result = " + tasks.take().get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("ReturnBApp has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}

}
