package de.udemy.Basic;

import java.util.ArrayList;
import java.util.List;

class Prozessor2{
	
	private List<Integer> list = new ArrayList<>();
	private final int LIMIT = 5;
	private final int BOTTOM = 0;
	private int val = 0;
	
	private final Object lock = new Object();
	
	public void produce() throws InterruptedException{
		synchronized (lock) {
			while(true) {
				if(list.size() >= LIMIT) {
					System.out.println("Warte darauf Items von der Liste zu entfernen");
					lock.wait();
				}else {
					list.add(val);
					System.out.println("Füge "+val++ +" hinzu!");
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}
	
	public void consume() throws InterruptedException{
		synchronized (lock) {
			while(true) {
				if(list.size() <= BOTTOM) {
					System.out.println("Warte darauf Items in List einzufügen");
					lock.wait();
				}else {
					System.out.println("Lösche "+list.remove(list.size()-1) +" aus Liste!");
					val--;
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}
}

public class Processor2App {

	public static void main(String[] args) {
		Prozessor2 pro = new Prozessor2();
		
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