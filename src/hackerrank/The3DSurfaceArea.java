package hackerrank;

import java.util.Scanner;

public class The3DSurfaceArea {
	static int surfaceArea(int[][] A) {
        int area = 2 * H * W;
        
        for(int i=1 ; i<=H ; i++){
            for(int j=1; j<=W ; j++) {
                area += max(0, A[i][j]-A[i][j-1]);
                area += max(0, A[i][j]-A[i-1][j]);
                area += max(0, A[i][j]-A[i][j+1]);
                area += max(0, A[i][j]-A[i+1][j]);
            }
        }
        
        return area;
    }
    
    private static int max(int a, int b) {
        if(a>=b)
            return a;
        return b;
    }

    private static int H;
    private static int W;
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        H = in.nextInt();
        W = in.nextInt();
        int[][] A = new int[H+2][W+2];
        for(int A_i = 1; A_i <= H; A_i++){
            for(int A_j = 1; A_j <= W; A_j++){
                A[A_i][A_j] = in.nextInt();
            }
        }
        int result = surfaceArea(A);
        System.out.println(result);
        in.close();
    }
}
