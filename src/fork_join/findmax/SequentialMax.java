package fork_join.findmax;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalInt;

public class SequentialMax {

    public Integer sequentialMaxFindingStreams(List<Integer> numbers,int highIndex){
        OptionalInt max = numbers.stream().mapToInt((v) -> v).max();
        if(max.isPresent())
            return max.getAsInt();
        return null;
    }

    public Integer sequentialMaxFindingParallelStreams(List<Integer> numbers,int highIndex){
        OptionalInt max = numbers.parallelStream().mapToInt((v) -> v).max();
        if(max.isPresent())
            return max.getAsInt();
        return null;
    }

    public int sequentialMaxFindingList(List<Integer> numbers,int highIndex){
       return Collections.max(numbers);
    }
    public int sequentialMaxFinding(List<Integer> numbers,int highIndex){
       int max=Integer.MIN_VALUE;
        for(int i=0;i<highIndex;i++){
            if (numbers.get(i)>max)
                max=numbers.get(i);
        }
        return max;
    }
}
