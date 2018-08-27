package candy;

import java.util.Scanner;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class Main {
    int numCandy;
    int money;
    Candy[] candies;
    int[] cache;

    Scanner scanner = new Scanner(System.in);

    public int solve(int money) {
        if (cache[money]!=0)
            return cache[money];

        for (int i=0;i<numCandy;++i) {
            if (money-candies[i].price>=0) {
                cache[money] = Math.max(cache[money], candies[i].calorie + solve(money - candies[i].price));


                //System.out.println("price:"+candies[i].price+", cal:"+candies[i].calorie+", Max cal:"+cache[money]);
            }
        }
        return cache[money];
    }

    void setUp() {
        numCandy = scanner.nextInt();
        money = (int)(scanner.nextFloat()*100+0.5);
        if (numCandy==0||money==0)
            return;

        candies = new Candy[numCandy];
        for (int i=0;i<numCandy;++i) {
            candies[i] = new Candy(scanner.nextInt(), (int)(scanner.nextFloat()*100+0.5));
        }
        cache = new int[money+1];
    }
    
    public static void main(String... args) {
        Main sol = new Main();
        String ret = "";

        while(true) {
            sol.setUp();
            if (sol.numCandy==0||sol.money==0)
                break;
            ret+=sol.solve(sol.money)+"\n";
        }
        System.out.println(ret);
    }
    
    static class Candy {
        int calorie;
        int price;

        public Candy(int calorie, int price) {
            this.calorie = calorie;
            this.price = price;
        }
    }
}
