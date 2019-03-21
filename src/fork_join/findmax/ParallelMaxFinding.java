package fork_join.findmax;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ParallelMaxFinding extends RecursiveTask<Integer> {

    private List<Integer> numbers;
    private int lowIndex, highIndex;

    public ParallelMaxFinding(List<Integer> numbers, int lowIndex, int highIndex) {
        this.numbers = numbers;
        this.lowIndex = lowIndex;
        this.highIndex = highIndex;
    }

    @Override
    protected Integer
    compute() {
        if (highIndex - lowIndex < 100000) {
            return sequentialMaxFinding();
        } else {
            int middle = (lowIndex + highIndex) / 2;
            ParallelMaxFinding task1 = new ParallelMaxFinding(numbers, lowIndex, middle);
            ParallelMaxFinding task2 = new ParallelMaxFinding(numbers, middle + 1, highIndex);
            invokeAll(task1, task2);
            return Math.max(task1.join(), task2.join());
        }

    }

    private Integer sequentialMaxFinding() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < highIndex; i++) {
            if (numbers.get(i) > max)
                max = numbers.get(i);
        }
        return max;
    }
}


