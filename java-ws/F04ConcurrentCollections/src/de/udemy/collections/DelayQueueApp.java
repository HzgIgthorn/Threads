package de.udemy.collections;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

class DelayWorker implements Delayed{

	private long duration;
	private String msg;
	
	public DelayWorker(long duration, String msg) {
		super();
		this.duration = System.currentTimeMillis() + duration;
		this.msg = msg;
	}

	@Override
	public int compareTo(Delayed other) {
		if(this.duration < ((DelayWorker)other).getDuration())
			return -1;
		if(this.duration > ((DelayWorker)other).getDuration())
			return 1;
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(duration-System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return this.msg;
	}
	
	
}

public class DelayQueueApp {

	public static void main(String[] args) {
		//Die DelayQueue kann beliebig viele Elemente speichern und gibt diese über die Methode
		//take wieder zurück, sobald das angegebene Delay abgelaufen ist.
		BlockingQueue<DelayWorker> queue = new DelayQueue<DelayWorker>();
		try {
			queue.put(new DelayWorker(1000, "Das ist die erste Nachricht"));
			queue.put(new DelayWorker(10000, "Das ist die zweite Nachricht"));
			queue.put(new DelayWorker(4000, "Das ist die dritte Nachricht"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		while(!queue.isEmpty()) {
			try {
				System.out.println(queue.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}