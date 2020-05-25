package de.udemy.threadapi;

public class SumObserver implements ResultListener<Integer> {
	
	public String taskId;
	
	public SumObserver(String taskId) {
		super();
		this.taskId = taskId;
	}

	@Override
	public void notifyResult(Integer result) {
		System.out.println("Result for " + taskId + " = " + result);

	}

}
