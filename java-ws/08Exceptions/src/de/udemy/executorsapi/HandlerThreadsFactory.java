package de.udemy.executorsapi;

import java.util.concurrent.ThreadFactory;

import de.udemy.threadapi.ThreadExceptionHandler;

public class HandlerThreadsFactory implements ThreadFactory {
	
	private static int count = 0;
	private String name;

	public HandlerThreadsFactory(String name) {
		super();
		this.name = name + "-";
	}

	@Override
	public Thread newThread(Runnable runner) {
		Thread t = new Thread(runner, name + ++count);
		t.setUncaughtExceptionHandler(new ThreadExceptionHandler());
		return t;
	}
}