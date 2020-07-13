package de.udemy.app;

public class MergeSort {
	
	private int[] nums;
	private int[] tempArray;
	private boolean asc;
	
	public MergeSort(int[] nums) {
		this(nums, true);
	}
	
	public MergeSort(int[] nums, boolean asc) {
		super();
		this.nums = nums;
		this.tempArray = new int[nums.length];
	}
	
	public void mergeSort(int low, int high) {
		if(asc)
			mergeSortAsc(low, high);
		else
			mergeSortDesc(low, high);
		
	}

	private void mergeSortAsc(int low, int high) {
		if(low >= high)
			return;
		if(Math.abs(high-low) <=1) {
			if(nums[high] < nums[low]) {
				int temp = nums[high];
				nums[high] = nums[low];
				nums[low]=temp;
			}
			return;
		}

		int middle = (low + high) / 2;
		mergeSortAsc(low, middle);
		mergeSortAsc(middle + 1, high);
		mergeAsc(low, middle, high);
	}
	
	private void mergeSortDesc(int low, int high) {
		if(low >= high)
			return;
		if(Math.abs(high-low) <=1) {
			if(nums[high] > nums[low]) {
				int temp = nums[high];
				nums[high] = nums[low];
				nums[low]=temp;
			}
			return;
		}

		int middle = (low + high) / 2;
		mergeSortDesc(low, middle);
		mergeSortDesc(middle + 1, high);
		mergeDesc(low, middle, high);
	}
	
	private void mergeAsc(int low, int middle, int high) {
		for(int i = low; i <= high; i++) {
			tempArray[i]= nums[i];
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		
		while(i <= middle && j <= high) {
			if(tempArray[i] <= tempArray[j]) {
				nums[k] = tempArray[i++];
			}else {
				nums[k] = tempArray[j++];
			}
			k++;
		}
		
		while(i <= middle) {
			nums[k++]=tempArray[i++];
		}
		while(j <= high) {
			nums[k++]=tempArray[j++];
		}
	}
	
	private void mergeDesc(int low, int middle, int high) {
		for(int i = low; i <= high; i++) {
			tempArray[i]= nums[i];
		}
		int i = low;
		int j = middle + 1;
		int k = low;
		
		while(i <= middle && j <= high) {
			if(tempArray[i] >= tempArray[j]) {
				nums[k] = tempArray[i++];
			}else {
				nums[k] = tempArray[j++];
			}
			k++;
		}
		
		while(i <= middle) {
			nums[k++]=tempArray[i++];
		}
		while(j <= high) {
			nums[k++]=tempArray[j++];
		}
	}

	public String showResult() {
		boolean hasFirst = false;
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < nums.length; i++) {
			if(hasFirst)
				builder.append("; "+nums[i]);
			else {
				hasFirst = true;
				builder.append(""+nums[i]);
			}
		}
		return builder.toString();
	}
	
	public boolean isSorted() {
		if(asc) {
			for(int i = 0; i < nums.length - 1; i++) {
				if(nums[i] > nums[i+1])
					return false;
			}
			return true;
		}
		for(int i = 0; i < nums.length - 1; i++) {
			if(nums[i] < nums[i+1])
				return false;
		}
		return true;
	}
}