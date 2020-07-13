package de.udemy.dining;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DiningApp implements Constants{

	public static void main(String[] args) {
		ExecutorService service = null;
		Philosopher[] philosopers = null;
		
		try {
			philosopers = new Philosopher[NUMBER_OF_PHILOSOPHERS];
			Chopstick[] chopsticks = new Chopstick[NUMBER_OF_CHOPSTICKS];
			service = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS);
			
			for(int i = 0; i < NUMBER_OF_CHOPSTICKS; i++) {
				chopsticks[i] = new Chopstick(i);
			}
			
			for(int i = 0; i < NUMBER_OF_PHILOSOPHERS; i++) {
				philosopers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1) % NUMBER_OF_CHOPSTICKS]);
				service.execute(philosopers[i]);
			}
			
			Thread.sleep(SIMULATION_RUNNING_TIME);
			
			for(Philosopher philosopher : philosopers) {
				philosopher.setFull(true);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			service.shutdown();
			
			while(!service.isTerminated())
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			for(Philosopher philosopher : philosopers)
				System.out.println(philosopher+" eats "+ philosopher.getEatingCounter()+" times");
		}
		
	}

}
