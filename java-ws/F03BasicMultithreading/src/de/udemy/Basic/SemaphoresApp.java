package de.udemy.Basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Downloader{
	INSTANCE;
	
	//Semaphore erlaubt genau 3 Threads gleichzeitig die downloadDate Methode aufzurufen
	private Semaphore semaphore = new Semaphore(3, true);
	
	public void downloadData() {
		try {
			semaphore.acquire();
			download();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaphore.release();
		}
	}
	
	private void download() {
		System.out.println("Herunterladen von Daten aus den Netz...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class SemaphoresApp {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 12; i++) {
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					Downloader.INSTANCE.downloadData();
				}
			});
		}
	}
}