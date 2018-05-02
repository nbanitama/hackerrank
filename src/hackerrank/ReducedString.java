package hackerrank;

import java.util.Scanner;

public class ReducedString {

	static String super_reduced_string(String s){
        String s1 = ""; 
        
        char x = 'a';
        int counter = 0;
        boolean isAdjecent = true;
        
        while(isAdjecent) {
            isAdjecent = false;
            if(s.length() > 0){
                s1 = "";
                x = s.charAt(0);
                counter = 0;
            }
            for(int i=0 ; i<s.length() ; i++) {
                if(x == s.charAt(i)){
                    counter ++;
                    if(counter > 1)
                        isAdjecent = true;
                }
                else {
                    if(counter%2 == 1)
                        s1 += x;
                    counter = 1;
                    x = s.charAt(i);
                }
            }
            if(counter%2 == 1){
                s1 += x;
                counter = 0;
            }
            s = s1;
        }
        
        if(s.length() == 0)
            return "Empty String";
        return s;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String result = super_reduced_string(s);
        System.out.println(result);
    }
	
}
