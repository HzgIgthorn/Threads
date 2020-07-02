package de.udemy.threadapi;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadExceptionHandler implements UncaughtExceptionHandler {

	String id;
	
	public ThreadExceptionHandler(String id) {
		this.id = id;
	}
	
	public ThreadExceptionHandler() {
		super();
	}

	@Override
	public void uncaughtException(Thread thread, Throwable thrown) {
		System.out.println(this + " caught exception in thread - \"" 
				+thread.getName() + "\" => " + thrown);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@"+this.hashCode()+((this.id == null || "".equals(id))?"":"(\""+id+"\")");
	}
}