package de.udemy.executorsapi;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;

public class FixedThreadPoolApp implements Constants{

	public static void main(String[] args) {
		Date before = new Date();
		System.out.println("Main thread starts at "+STF.format(before));
		
		//Ein FixedThreadPool bekommt eine Konstante übergeben, die die maximal Anzahl von Thread angibt
		//die dieser ThreadPool zur Verfügung stellt. Wenn gerade kein Thread vorhanden ist, müssen die
		//Runnables in einer Queue auf die Ausführung warten
		ExecutorService execServ = Executors.newFixedThreadPool(3);
		
		execServ.execute(new LoopTaskA());
		execServ.execute(new LoopTaskA());
		execServ.execute(new LoopTaskA());
		
		execServ.execute(new LoopTaskA());
		execServ.execute(new LoopTaskA());
		
		//Der ExecutorService beendet nach Shutdown noch die vorhandenen Tasks, aber nimmt keine neuen mehr an.
		execServ.shutdown();
		
		System.out.println("Main thread has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+"  millis");
	}
}