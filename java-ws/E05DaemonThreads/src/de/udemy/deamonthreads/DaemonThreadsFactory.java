package de.udemy.deamonthreads;

public class DaemonThreadsFactory extends NamedThreadsFactory {

	public DaemonThreadsFactory(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Thread newThread(Runnable runner) {
		Thread t = super.newThread(runner);
		
		//Jeder 2. Thread wird als Daemon-Thread definiert. Damit brechen diese abbrupt ab, wenn alle normalen User-Threads fertig sind
		if(count % 2 == 0)
			t.setDaemon(true);
		return t;
	}
}