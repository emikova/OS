package V3;

public class MyThread extends Thread{
    String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run(){
        System.out.println("Hello from a thread " + name);
    }



}