package de.udemy.threadapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import de.udemy.threadapi.Constants;

public class LoopInterruptTask implements Runnable, Constants{
	
	public static final String name = "LoopTaskF";
	public String thread;

	private static int count = 0;
	private int id;
	Date before;
	
	public static int FAK = 3000;
	
	public LoopInterruptTask() {
		super();
		before = new Date();
		thread = Thread.currentThread().getName();
		this.id = ++count;
		System.out.println(thread+" " +name+id+" starts at "+STF.format(before));
	}

	@Override
	public void run() {
		this.thread = Thread.currentThread().getName();
		for(int i = 1; true; i++ ) {
			
			System.out.println("<"+thread+"> TICK TICK " + i);
			
			//das Interrupted Flag führt zu einer Exception, wenn der Thread schläft. Deshalb lassen wir ihn wirklich arbeiten
			soSomeWork();
			
			if(Thread.interrupted()) {
				System.out.println(thread+" has finished in "+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
				break;
			}
			System.out.println("Interrupt State is again: "+Thread.interrupted());
		}
	}

	private void soSomeWork() {
		for(int i = 0; i < 2; i++) {
			Collections.sort(generateDataSet());
		}
		
	}

	private List<Integer> generateDataSet() {
		List<Integer> intList = new ArrayList<>();
		
		for(int i = 0; i < DATA_SIZE; i++) {
			intList.add(RAND.nextInt(DATA_SIZE));
		}
		return intList;
	}
}