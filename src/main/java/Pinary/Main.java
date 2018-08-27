package Pinary;

import java.util.Scanner;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class Main {
    static int input;
    static long[] cache;

    static void setUp() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextInt();
        cache = new long[input+3];
        cache[1] = 1;
        cache[2] = 1;
    }

    static long solve() {
        long ret = 0;

        ret = pinary(input);

        return ret;
    }

    static long pinary(int dest) {
        if (cache[dest]!=0)
            return cache[dest];

        long ret = 0;

        for(int i=1 ; i<=dest-2 ; ++i) {
            if(cache[i] != 0) {
                ret += cache[i];
                continue;
            }
            long tmpSum = 0;
            for(int j=1 ; j<=i-2 ; ++j) {
                tmpSum += cache[j];
            }
            cache[i] = ++tmpSum;
            ret +=cache[i];
        }

        return ret+1;
    }

    public static void main(String... args) {
        setUp();
        System.out.println(solve());
    }
}
