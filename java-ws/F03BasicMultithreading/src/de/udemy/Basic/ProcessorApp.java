package de.udemy.Basic;

class Prozessor{
	public void produce() throws InterruptedException{
		synchronized (this) {
			System.out.println("Wir sind der produce-Methode");
			//wait sagt allen Threads die auf dem gleichen Object synchronisiert sind, dass sie 
			//zuerst das Object benutzen dürfen
			wait(10000);
			System.out.println("Methode produce nach wait");
		}
	}
	
	public void consume() throws InterruptedException{
		Thread.sleep(1000);
		
		synchronized (this) {
			System.out.println("Methode consume...");
			notify();
		}
	}
}

public class ProcessorApp {

	public static void main(String[] args) {
		Prozessor pro = new Prozessor();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					pro.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					pro.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}