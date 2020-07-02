package de.udemy.Basic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

class Worker implements Runnable{
	
	/**
	 * Das Schlüsselwort volatile sorgt dafür, dass die Variable isTerminated nicht im Cache gespeichert wird.
	 * Das ist hier sinnvoll, weil auf Variablen im Cache nur der Thread zugreifen kann, der diese auch
	 * da gespeichert hat. Das Schlüsselwort volatile macht allerdings ein Programm langsammer, weshalb es nur
	 * eingesetzt werden sollte, wenn das unbedingt notwendig ist.
	 */
	private volatile boolean isTerminated = false;

	@Override
	public void run() {
		while(!isTerminated) {
			System.out.println("Hallo aus der Worker Classe");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}
}

public class WorkerApp {
	
	static SimpleDateFormat STF = new SimpleDateFormat("HH:mm:ss");

	public static void main(String[] args) {
		
		Date before = new Date();
		System.out.println(ThreadIIApp.class.getSimpleName()+" starts at "+STF.format(before));
		
		Worker worker = new Worker();
		
		Thread t1 = new Thread(worker);
		t1.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		worker.setTerminated(true);
		System.out.println(ThreadIIApp.class.getSimpleName()+" has finished in "
				+TimeUnit.MILLISECONDS.convert(new Date().getTime() - before.getTime(), TimeUnit.MILLISECONDS)+" millis");
	}
}