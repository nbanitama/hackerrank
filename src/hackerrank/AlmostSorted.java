package hackerrank;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class AlmostSorted {

    static boolean isSorted;
    static boolean isSwapped;
    static boolean mode;
    static int first;
    static int second;
    
    static void almostSorted(int[] arr) {
        isSorted = true;
        isSwapped = false;
        first = -1;
        second = -1;
        mode = false;
        
        customQuickSort(arr);
        
        if(isSorted){
            System.out.println("yes");
            if(first > 0 && second > 0){
                if(!mode)
                    System.out.print("swap ");
                else
                    System.out.print("reverse ");
                System.out.println(first + " " + second);
            }
        }
        else {
            System.out.println("no");
        }
    }
    
    static int findPivot(int[] data){
        for(int i=data.length-1 ; i>0 ; i--){
            if(data[i] < data[i-1])
                return i;
        }
        return 0;
    }
    
    static void customQuickSort(int[] data) {
        int pivot = findPivot(data);
        
        boolean increment = true;
        for(int i=0; i<pivot && increment; i++) {
            if(data[i] <= data[pivot]) {
                if(data[i] > data[i+1])
                    increment = false;
            } else {
                increment = false;
                if(!isSwapped){
                    if(checkDecre(data, i, pivot)){
                        first = i+1;
                        second = pivot+1;
                        increment = true;
                    } 
                    int temp = data[pivot];
                    data[pivot] = data[i];
                    data[i] = temp;
                    if(checkInc(data, i, pivot)){
                        increment = true;
                        first = i+1;
                        second = pivot+1;
                    }
                    if(pivot < data.length-1)
                        if(data[pivot] > data[pivot+1])
                            increment = false;
                }
                break;
            }
        }
        if(increment)
            isSorted = true;
        else
            isSorted = false;
    }
    
    static boolean checkDecre(int[] data, int start, int end) {
        for(int i=start+1 ; i<=end ; i++){
            if(data[i] > data[i-1])
                return false;
        }
        mode = true;
        return true;
    }
    
    static boolean checkInc(int[] data, int start, int end) {
        for(int i=start+1 ; i<=end ; i++){
            if(data[i] < data[i-1])
                return false;
        }
        mode = false;
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for(int arr_i = 0; arr_i < n; arr_i++){
            arr[arr_i] = in.nextInt();
        }
        almostSorted(arr);
        in.close();
    }
}
