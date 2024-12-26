package V5;


import java.util.Arrays;
import java.util.Map;

class Stack{
    private int[] elements ;
    private int last_index;

    Stack(int capacity){
        elements = new int[capacity];
        last_index = -1;
    }

    public boolean isEmpty(){
        if (last_index == -1){
            return true;
        }
        return false;

    }

    public boolean isFull(){
        if(last_index == elements.length-1){
            return true;
        }
        return false;
    }

    public synchronized void push(int element){
        while (this.isFull()){
            System.out.println("Stack is full! Wait...");
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("I'm adding the element: " + element);
        elements[++last_index] = element;
        notify();
    }

    public synchronized int pop(){
        while (this.isEmpty()){
            System.out.println("Stack is empty! Wait...");
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("I'm popping the element!");
        int element = elements[last_index--];
        notify();
        System.out.println("Element  " + element + "  is popped!");
        return element;
    }
}

class ThreadA extends Thread{
    private Stack s;
    public ThreadA(Stack s){
        this.s = s;
    }

    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            s.push(i);
        }
    }
}

class ThreadB extends Thread{
    private Stack s;

    public ThreadB(Stack s){
        this.s = s;
    }

    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            s.pop();
        }
    }
}
public class FirstExercise {
    public static void main(String[] args) {
        Stack stack = new Stack(10);
        ThreadA threadA = new ThreadA(stack);
        ThreadB threadB = new ThreadB(stack);

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
