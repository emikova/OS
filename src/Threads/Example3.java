package Threads;


class SharedSource{
    private int value;
    private boolean isSet = false;

    public synchronized void setValue(int value){
        this.value = value;
        isSet = true;
        notify();
    }

    public synchronized int getValue(){
        if(!isSet){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return value;
    }

}

class ThreadA extends Thread{
    public SharedSource sharedSource;
    public ThreadA(SharedSource sharedSource){
        this.sharedSource = sharedSource;
    }

    @Override
    public void run(){
        System.out.println("Cekamo da se vrijednost postavi!");
        int t = sharedSource.getValue();
        System.out.println("Vrijednost: " + t);
    }
}

class ThreadB extends Thread{
    public SharedSource sharedSource;

    public ThreadB(SharedSource sharedSource){
        this.sharedSource = sharedSource;
    }

    @Override
    public void run(){
        System.out.println("Postavljam vrijednost: " );
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        sharedSource.setValue(10);
        System.out.println("Vrijednost postavljena je: " + sharedSource.getValue());
    }
}
public class Example3 {
    public static void main(String[] args) {
        SharedSource sharedSource = new SharedSource();
        ThreadA t1 = new ThreadA(sharedSource);
        ThreadB t2 = new ThreadB(sharedSource);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Shared source: " + sharedSource.getValue());
    }

}
