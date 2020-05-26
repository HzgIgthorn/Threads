package de.udemy.deamonthreads;

import java.util.concurrent.ThreadFactory;

public class NamedThreadsFactory implements ThreadFactory {
	
	protected static int count = 0;
	protected String name;

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