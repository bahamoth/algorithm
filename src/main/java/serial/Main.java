package serial;

import java.util.Scanner;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class Main {
    int num;
    int[] numList;
    int[] cache;

    void setUp() {
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();

        numList = new int[num];
        for(int i=0;i<num;++i) {
            numList[i] = scanner.nextInt();
        }
        cache = new int[num];
    }

    int solve() {
        int ret=numList[0];

        cache[0] = numList[0];
        for(int i=1 ; i<num ; ++i) {
            if(cache[i-1]+numList[i] > numList[i])
                cache[i] = cache[i-1]+numList[i];
            else
                cache[i] = numList[i];
            ret = cache[i] > ret ? cache[i] : ret;
        }

        return ret;
    }

    public static void main(String... args) {
        Main sol = new Main();
        sol.setUp();
        System.out.println(sol.solve());
    }
}
