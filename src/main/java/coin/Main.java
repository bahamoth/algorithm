package coin;

import java.util.Scanner;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class Main {
    int numCoin;
    int sum;
    int[] coins;

    void setUp() {
        Scanner scanner = new Scanner(System.in);
        numCoin = scanner.nextInt();
        sum = scanner.nextInt();
        coins = new int[numCoin];
        for (int i=0 ; i<numCoin ; ++i) {
            coins[i] = scanner.nextInt();
        }
    }

    void setUp(boolean isTest) {
        if (isTest==false) {
            setUp();
            return;
        }
        numCoin = 3;
        sum = 15;
        coins = new int[]{1,2,5,12};

    }

    public static void main(String... args) {

    }
}
