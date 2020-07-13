package de.udemy.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class ConcurrentWorker1 implements Runnable{

	private ConcurrentMap<String, Integer> map;
	
	public ConcurrentWorker1(ConcurrentMap<String, Integer> map) {
		super();
		this.map = map;
	}

	@Override
	public void run() {

		try {
			map.put("B", 1);
			map.put("H", 2);
			Thread.sleep(1000);
			map.put("F", 3);
			map.put("A", 4);
			Thread.sleep(1000);
			map.put("E", 5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ConcurrentWorker2 implements Runnable{

	private ConcurrentMap<String, Integer> map;
	
	public ConcurrentWorker2(ConcurrentMap<String, Integer> map) {
		super();
		this.map = map;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(5000);
			System.out.print(map.get("A")+" ");
			Thread.sleep(1000);
			System.out.print(map.get("E")+" ");
			System.out.print(map.get("F")+" ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class ConcurrentApp {

	public static void main(String[] args) {
		ConcurrentMap<String, Integer> map = new ConcurrentHashMap<>();
		new Thread(new ConcurrentWorker1(map)).start();
		new Thread(new ConcurrentWorker2(map)).start();
	}
}