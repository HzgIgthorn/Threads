package de.udemy.app;

import java.util.Random;

public class MergeApp {
	
	private static final int ANZ = 30;
	private static final int MIN = -500;
	private static final int MAX = 500;

	public static void main(String[] args) {
		Random random = new Random();
		int[] nums = new int[ANZ];
		
		for(int i = 0; i < nums.length; i++) {
			nums[i] = random.nextInt(MAX - MIN) + MIN;
		}
		
		MergeSort sort = new MergeSort(nums ,true);
		sort.mergeSort(0, nums.length - 1);
		if(sort.isSorted()) {
			System.out.println("Korrekt: " + sort.showResult());
		}else
			System.out.println("Fehler: " + sort.showResult());

	}

}
