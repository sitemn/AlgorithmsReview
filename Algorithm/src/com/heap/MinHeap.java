package com.heap;

import java.util.Random;

public class MinHeap <E extends Comparable<E>>{
    private Array<E> data;

    public MinHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MinHeap(){
        data = new Array<>();
    }

    public MinHeap(E[] arr){
        data = new Array<>(arr);
        if(arr.length != 1){
            for (int i = parent(arr.length - 1); i >=0; i--) {
                siftDown(i);
            }
        }
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public int parent(int index){
        if(index==0){
            throw new IllegalArgumentException("root does not have a parent!");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k){
        while(k>0 && data.get(parent(k)).compareTo(data.get(k)) > 0){
            data.swap(parent(k), k);
            k = parent(k);
        }
    }

    public E findMin(){
        if(data.getSize() == 0){
            throw new IllegalArgumentException("Heap is empty!");
        }
        return data.get(0);
    }

    public E extractMin(){
        E ret = data.get(0);
        data.swap(data.getSize() - 1, 0);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k){
        while(leftChild(k) < data.getSize()){
            int j = leftChild(k);
            if(j + 1 < data.getSize() && data.get(j+1).compareTo(data.get(j)) < 0){
                j++;
            }

            if(data.get(k).compareTo(data.get(j)) <= 0){
                break;
            }

            data.swap(k, j);
            k = j;
        }
    }

    public E replace(E e){
        E ret = findMin();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    public static void main(String[] args) {

        int n = 1000000;

        MinHeap<Integer> minHeap = new MinHeap<>();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            minHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = minHeap.extractMin();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] > arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MinHeap completed.");
    }
}
