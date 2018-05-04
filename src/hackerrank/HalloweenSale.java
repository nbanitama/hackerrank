package hackerrank;

import java.util.Scanner;

public class HalloweenSale {
	static int howManyGames(int p, int d, int m, int s) {
        // Return the number of games you can buy
        int counter = 0;
        int sum = p;
        while(sum <= s) {
            //System.out.println(sum);
            if(p <= m){
                sum += m;
            }
            else {
                if(p-d >= m)
                    p -= d;
                else
                    p = m;
                sum += p;
            }
            counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        int d = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int answer = howManyGames(p, d, m, s);
        System.out.println(answer);
        in.close();
    }
}
