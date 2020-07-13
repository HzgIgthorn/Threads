package de.udemy.dining;

import java.util.Random;

public class Philosopher implements Runnable{
	
	private static Random RAND = new Random();
	
	private int id;
	private Chopstick leftC;
	private Chopstick rightC;
	
	private int eatingCounter;
	private volatile boolean full = false;
	
	public Philosopher(int id, Chopstick leftC, Chopstick rightC) {
		this.id = id;
		this.leftC = leftC;
		this.rightC = rightC;
	}

	@Override
	public void run() {
		while(!full) {
			try {
				think();
				if(this.leftC.pickUp(this, State.LEFT)) {
					if(this.rightC.pickUp(this, State.RIGHT)) {
						eat();
						this.rightC.putDown(this, State.RIGHT);
					}
					this.leftC.putDown(this, State.LEFT);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

	private void think() throws InterruptedException {
		System.out.println(this+" is thinking... ");
		Thread.sleep(RAND.nextInt(1000));
	}
	
	private void eat() throws InterruptedException {
		System.out.println(this + " is eating...");
		this.eatingCounter++;
		Thread.sleep(RAND.nextInt(1000));
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	@Override
	public String toString() {
		return "Philosopher "+ id;
	}

	public int getEatingCounter() {
		return eatingCounter;
	}
}
