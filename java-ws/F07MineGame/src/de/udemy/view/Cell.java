package de.udemy.view;

import java.awt.GridLayout;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JPanel;

import de.udemy.Constants.State;

public class Cell extends JPanel {
	private static final long serialVersionUID = 1L;
	private int id;
	private Lock lock;
	private State state;
	private boolean hasBomb;
	
	public Cell(int id) {
		init(id);
		setLayout(new GridLayout());
	}

	private void init(int id) {
		this.id = id;
		this.lock = new ReentrantLock();
		this.state = State.EMPTY;
		this.hasBomb = false;
	}
	
	public void lock() {
		try {
			lock.tryLock(10, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void unlock() {
		lock.unlock();
	}

	@Override
	public String toString() {
		return id + "=" + state + "->" + hasBomb;
	}
}
