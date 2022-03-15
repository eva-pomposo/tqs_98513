package com.tqs.tqsStack;

import java.util.LinkedList;

public class TqsStack<T> 
{
    private LinkedList<T> lst = new LinkedList<>();
    private int bound = -1;

    public TqsStack(){

    }
    
    public TqsStack(int bound){
        this.bound = bound;
    }

    public void push(T elem){
        if (bound != -1) {
            if (size() < bound) {
                lst.addFirst(elem);
            } else {
                throw new IllegalStateException();
            }
        } else {
            lst.addFirst(elem);
        }
    }

    public T pop(){
        return lst.removeFirst();
    }

    public T peek(){
        return lst.getFirst();
    }

    public int size(){
        return lst.size();
    }

    public boolean isEmpty(){
        return lst.isEmpty();
    }

}
