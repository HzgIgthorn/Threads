package de.udemy.executorapi;

import java.util.concurrent.ThreadFactory;

public class NamedThreadsFactory implements ThreadFactory {
	
	private static int count = 0;
	private String name;

	public NamedThreadsFactory(String name) {
		super();
		this.name = name + "-";
	}

	@Override
	public Thread newThread(Runnable runner) {
		Thread t = new Thread(runner, name + ++count);
		return t;
	}
}