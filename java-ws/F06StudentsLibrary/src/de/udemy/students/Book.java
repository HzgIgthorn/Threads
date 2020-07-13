package de.udemy.students;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

	private int id;
	private Lock lock;

	public Book(int id) {
		super();
		this.id = id;
		this.lock = new ReentrantLock();
	}

	public void read(Student student) {
		try {
			// Wir können an dieser Stelle auch lock.lock() verwenden. Dann gibt es aber
			// nicht die Möglichkeit
			// in der Zwischenzeit etwas Anderes zu tun.
			if (lock.tryLock(1, TimeUnit.MINUTES)) {
				System.out.println(student + " startet mit Lesen von " + this);
				Thread.sleep(2000);
				System.out.println(student + " beendet das Lesen von " + this);
				lock.unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "Book #" + id;
	}
}