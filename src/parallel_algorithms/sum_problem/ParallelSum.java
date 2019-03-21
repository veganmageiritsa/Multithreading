package parallel_algorithms.sum_problem;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class ParallelSum {

	private ParallelWorker[] sums;
	private int numOfThreads;
	
	public ParallelSum(int numOfThreads) {
		this.sums = new ParallelWorker[numOfThreads];
		this.numOfThreads = numOfThreads;
	}
	
	public int parallelSum(int[] nums) {
		
		int size = (int) Math.ceil(nums.length * 1.0 / numOfThreads);


		IntStream.range(0, numOfThreads).forEach(i -> {
			sums[i] = new ParallelWorker(nums, i * size, (i + 1) * size);
			sums[i].start();
		});

		try {
			for (ParallelWorker sum : sums) {
				sum.join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int total = 0;

		for (ParallelWorker sum : sums) {
			total += sum.getPartialSum();
		}

		return total;
	}

}
