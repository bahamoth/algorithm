package palindrome;

import java.util.Scanner;

/**
 * descriptions
 *
 * @author baham
 * @since 1.0.0
 */
public class Main {
    int numSeries;
    int[] series;
    int numPal;
    int[][] pal;
    boolean[][] cache;

    void setUp() {
        Scanner scanner = new Scanner(System.in);
        numSeries = scanner.nextInt();
        series = new int[numSeries];
        for(int i=0;i<numSeries;++i) {
            series[i] = scanner.nextInt();
        }
        numPal = scanner.nextInt();
        pal = new int[numPal][2];
        for(int i=0;i<numPal;++i) {
            pal[i][0] = scanner.nextInt()-1;
            pal[i][1] = scanner.nextInt()-1;
        }
        cache = new boolean[numSeries][numSeries];
    }

    boolean isPalindrome(int[] series, int start, int end) {
        if (start==end)
            return true;
        if (cache[start][end])
            return true;
        //System.out.println("start:"+start+" end:"+end);
        for (int i=start, j=end ; i<(end-start+1)/2+start ; ++i, --j) {
            if (series[i]!=series[j]) {
                //System.out.println("not pal "+series[i]+" : "+series[j]);
                return false;
            }
            //System.out.println("("+i+","+j+") "+series[i]+" : "+series[j]);
        }
        cache[start][end]=true;
        return true;
    }

    public void solve() {
        //System.out.println(Arrays.toString(series));
        for(int i=0 ; i<numPal ; ++i) {
            System.out.println(isPalindrome(series, pal[i][0], pal[i][1]) ? 1 : 0);
        }
    }

    public static void main(String... args) {
        Main sol = new Main();
        sol.setUp();
        sol.solve();
    }
}
