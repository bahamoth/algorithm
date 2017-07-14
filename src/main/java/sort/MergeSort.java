package sort;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class MergeSort {
    static Function<int[], int[]> mergeSort;
    static BinaryOperator<int[]> merge = (x, y)->{
        int[] ret = new int[x.length+y.length];

        for(int i=0, j=0, k=0 ; i<ret.length ; ++i) {
            if (j==x.length)
                ret[i] = y[k++];
            else if (k==y.length)
                ret[i] = x[j++];
            else
                ret[i] = x[j] < y[k] ? x[j++] : y[k++];
        }

        return ret;
    };
    public static void main(String... args) {
        mergeSort = x -> {
            if (x.length==1)
                return x;
            return merge.apply(mergeSort.apply(Arrays.copyOfRange(x, 0, x.length/2)),
                    mergeSort.apply(Arrays.copyOfRange(x, x.length/2, x.length)));
        };

        int[] test = new int[]{7,4,7,4,4,2,2,1,1,7,8,89,2};

        System.out.println(Arrays.toString(mergeSort.apply(test)));
    }
}
