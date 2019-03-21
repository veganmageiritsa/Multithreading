package fork_join.findmax;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class App {

    public static void main(String[] args) {
        Random random = new Random();

        SequentialMax sequentialMax=new SequentialMax();
        int cap=100000000;
        List<Integer> nums = new ArrayList<>(cap);


        IntStream.range(0, cap).forEach(i -> nums.add(random.nextInt(1000000) + 1));
        ParallelMaxFinding parallelMaxFinding=new ParallelMaxFinding(nums,0,cap-1);

        long start = System.currentTimeMillis();

        System.out.println("Max is " + sequentialMax.sequentialMaxFindingStreams(nums,nums.size()));

        System.out.println("Stream : " + (System.currentTimeMillis() - start)+"ms");

        start = System.currentTimeMillis();

        System.out.println("Max is " +sequentialMax.sequentialMaxFindingList(nums,cap));

        System.out.println("Collections Utilities : " + (System.currentTimeMillis() - start)+"ms");

        start = System.currentTimeMillis();

        System.out.println("Max is " +sequentialMax.sequentialMaxFindingParallelStreams(nums,cap));

        System.out.println("Parallel Stream: " + (System.currentTimeMillis() - start)+"ms");

        start = System.currentTimeMillis();

        System.out.println("Max is " +sequentialMax.sequentialMaxFinding(nums,cap));

        System.out.println("Single for Loop: " + (System.currentTimeMillis() - start)+"ms");

        start = System.currentTimeMillis();

        System.out.println("Max is " +sequentialMax.sequentialMaxFinding(nums,cap));

        System.out.println("Single for Loop: " + (System.currentTimeMillis() - start)+"ms");

        start = System.currentTimeMillis();

        System.out.println("Max is " +parallelMaxFinding.compute());

        System.out.println("Single for Loop: " + (System.currentTimeMillis() - start)+"ms");
    }
}
