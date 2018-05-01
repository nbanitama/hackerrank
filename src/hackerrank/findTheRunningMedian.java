package hackerrank;

import java.util.Arrays;
import java.util.Scanner;

public class findTheRunningMedian {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        higher = new MinHeap();
        lower = new MaxHeap();
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
            add(a[a_i]);
            rebalance();
            
            System.out.println(getMedian());
        }
    }
    
    private static MinHeap higher;
    private static MaxHeap lower;
    
    private static void add(int item) {
    	if(lower.size == 0 || lower.peek()>item) {
    		//System.out.println(item + " is inserted into lower");
       		lower.add(item);
    	} else {
    		//System.out.println(item + " is inserted into higher");
    		higher.add(item);
    	}
    }
    
    private static void rebalance() {
    	if(lower.size - higher.size >= 2) {
    		//System.out.println(lower.peek() + " is moved into higher");
    		higher.add(lower.poll());
    	}
    	if(higher.size - lower.size >= 2) {
    		//System.out.println(lower.peek() + " is moved into higher");
    		lower.add(higher.poll());
    	}
    }
    
    private static double getMedian() {
    	if(lower.size == higher.size) {
    		//System.out.println("The heap is even");
    		return (double)(lower.peek()+higher.peek())/2;
    	}
    	//System.out.println("The heap is odd");
    	if(lower.size > higher.size)
    		return (double)lower.peek();
    	return (double)higher.peek();
    }
}

abstract class Heap {
    protected int[] items;
    protected int size;
    protected int capacity;
    
    public Heap() {
        this.size = 0;
        this.capacity = 10;
        this.items = new int[this.capacity];
    }
    
    protected int getLeftChildIndex(int parentIndex) {
        return 2*parentIndex + 1;
    }
    protected int getRightChildIndex(int parentIndex) {
        return 2*parentIndex + 2;
    }
    protected int getParentIndex(int childIndex){
        return (childIndex-1)/2;
    }
    protected boolean hasLeftChild(int index) {
        return this.getLeftChildIndex(index) < this.size;
    }
    protected boolean hasRightChild(int index){
        return this.getRightChildIndex(index) < this.size;
    }
    protected boolean hasParent(int index) {
        return this.getParentIndex(index) >= 0;
    }
    private void ensureAddCapacity() {
        if(this.size == this.capacity){
            this.capacity *= 2;
            this.items = Arrays.copyOf(this.items, this.capacity);
        }
    }
    protected void swap(int index1, int index2) {
        int temp = this.items[index1];
        this.items[index1] = this.items[index2];
        this.items[index2] = temp;
    }
    public void add(int item) {
        this.ensureAddCapacity();
        this.items[this.size++] = item;
        this.heapifyUp();
        /*for(int i=0 ; i<this.size ; i++){
            System.out.print(this.items[i] + " ");
        }
        System.out.println();*/
    }
    
    public abstract void heapifyUp();
    public abstract void heapifyDown();
    
    public int poll() {
        if(this.size == 0)
            throw new IllegalStateException();
        int temp = this.items[0];
        this.items[0] = this.items[size-1];
        this.size -= 1;
        this.heapifyDown();
        return temp;
    }
    
    public int peek() {
        if(this.size == 0)
            throw new IllegalStateException();
        return this.items[0];
    }        
}

class MinHeap extends Heap{
    public void heapifyUp(){
        int index = this.size - 1;
        while(this.hasParent(index) && this.items[this.getParentIndex(index)] > this.items[index]) {
            this.swap(this.getParentIndex(index), index);
            index = this.getParentIndex(index);
        }
    }
    public void heapifyDown(){
        int index = 0;
        while(this.hasLeftChild(index)) {
            int smallerIndex = this.getLeftChildIndex(index);
            if(this.hasRightChild(index)){
                smallerIndex = this.items[this.getLeftChildIndex(index)] <= this.items[this.getRightChildIndex(index)] ? smallerIndex : this.getRightChildIndex(index);
            }
            if(this.items[index] > this.items[smallerIndex]){
                this.swap(index, smallerIndex);
                index = smallerIndex;   
            } else
                break;
        }
    }
}

class MaxHeap extends Heap{
    public void heapifyUp(){
        int index = this.size - 1;
        while(this.hasParent(index) && this.items[this.getParentIndex(index)] < this.items[index]) {
            this.swap(this.getParentIndex(index), index);
            index = this.getParentIndex(index);
        }
    }
    public void heapifyDown(){
        int index = 0;
        while(this.hasLeftChild(index)) {
            int higherIndex = this.getLeftChildIndex(index);
            if(this.hasRightChild(index)){
                higherIndex = this.items[this.getLeftChildIndex(index)] >= this.items[this.getRightChildIndex(index)] ? higherIndex : this.getRightChildIndex(index);
            }
            if(this.items[index] < this.items[higherIndex]){
                this.swap(index, higherIndex);
                index = higherIndex;   
            } else
                break;
        }
    }
}