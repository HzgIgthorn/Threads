package de.udemy.collections;

import java.util.concurrent.Exchanger;

class Runner1 implements Runnable{
	private int counter;
	private Exchanger<Integer> exchanger;
	
	public Runner1(Exchanger<Integer> exchanger) {
		super();
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while(true) {
			counter++;
			System.out.println(this.getClass().getSimpleName() + " zählt Counter hoch: " + counter);
			try {
				counter = exchanger.exchange(counter);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Runner2 implements Runnable{
	private int counter;
	private Exchanger<Integer> exchanger;
	
	public Runner2(Exchanger<Integer> exchanger) {
		super();
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while(true) {
			counter--;
			System.out.println(this.getClass().getSimpleName() + " zählt Counter runter: " + counter);
			try {
				counter = exchanger.exchange(counter);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class ExchangerApp {

	public static void main(String[] args) {
		//Durch den Exchanger bearbeiten die beiden Threads den Counter gemeinsam. 
		//Die beiden Threads warten auch aufeinander, da die Variable immer hin und her gereicht wird
		Exchanger<Integer> exchanger = new Exchanger<>();
		
		new Thread(new Runner1(exchanger)).start();
		new Thread(new Runner2(exchanger)).start();
	}
}