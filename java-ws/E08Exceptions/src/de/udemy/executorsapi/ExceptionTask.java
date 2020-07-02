package de.udemy.executorsapi;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionTask implements Runnable{
	
	static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");
	public static final String name = "Task";
	public String thread;

	private int id;
	Date before;
	
	public static int FAK = 3000;
	
	public ExceptionTask() {
		super();
		before = new Date();
		thread = Thread.currentThread().getName();
		System.out.println(thread+" " +name+id+" starts at "+STF.format(before));
	}

	@Override
	public void run() {
		throw new RuntimeException();
	}
}