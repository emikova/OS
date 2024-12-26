package V4;

public class CounterTwo{
    private int counter = 0;

    public CounterTwo(){
        this.counter = counter;
    }

    public int getCounter(){
        return counter;
    }

    public void incrementCounterTwo(){
        counter++;
    }
}

 class Example2 implements Runnable{
    private int thread_id;
    CounterTwo c;
    public Example2(int thread_id, CounterTwo c){
        this.thread_id = thread_id;
        this.c = c;
    }

    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            c.incrementCounterTwo();
            System.out.println("Thread: " + thread_id + " counter " + c.getCounter());
        }
    }

    public static void main(String[] args) {
        CounterTwo counter = new CounterTwo();
        Thread t1 = new Thread(new Example2(0,counter));
        Thread t2 = new Thread(new Example2(1, counter));

        t1.start();
        t2.start();

        /*try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        System.out.println("Counter : " + counter.getCounter());
    }
}

