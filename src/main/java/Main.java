import java.util.Scanner;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class Main {
    int input;
    int[] cache;

    void setUp() {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextInt();
        cache = new int[input+4];
        cache[1] = 0;
        cache[2] = 1;
        cache[3] = 1;
    }

    void setUp(int testInput) {
        this.input = testInput;
        cache = new int[input+4];
        cache[1] = 0;
        cache[2] = 1;
        cache[3] = 1;
    }

    Main() {
        setUp();
    }

    Main(int testInput) {
        setUp(testInput);
    }

    int smallest(int a, int b, int c) {
        int ret = a;
        if (ret>b)
            ret = b;
        if (ret>c)
            ret = c;
        return ret;
    }

    int smallest(int a, int b) {
        if (a>b)
            return b;
        return b;
    }

    int solve(int init, int input, int count) {
        if (input == 1) {
            if (cache[init] > count || cache[init] == 0)
                cache[init] = count;
            return count;
        }
        if (cache[init] <= count && cache[init] != 0)
            return cache[init];
        if (input%3==0) {
            return smallest(
                    solve(init, input / 3, count + 1),
                    solve(init, input - 1, count + 1)
            );
        }
        else if (input%2==0) {
            return smallest(
                    solve(init, input / 2, count + 1),
                    solve(init, input - 1, count + 1)
            );
        }
        return solve(init, input-1, count + 1);
    }

    public static void main(String... args) {
        Main solution = new Main(1000000);
        long elapsed = System.nanoTime();
        solution.solve(solution.input, solution.input, 0);
        System.out.println((System.nanoTime()-elapsed)/1000_000.0);
        System.out.println(solution.cache[solution.input]);
    }
}
