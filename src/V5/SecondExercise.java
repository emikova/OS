package V5;

class BinaryCounter{
    private int counter = 0;
    public void increment(){
        counter++;
    }

    public int getCounter(){
        return counter;
    }
}

class SecondExerciseRunnable implements Runnable{
    int thread_id;
    BinaryCounter counter;
    static BinarySemaphore bs = new BinarySemaphore();

    public SecondExerciseRunnable(int thread_id, BinaryCounter counter){
        this.thread_id = thread_id;
        this.counter = counter;
    }

    @Override
    public void run(){
        for(int i = 0; i < 100; i++){
            try {
                bs.aquire();

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            counter.increment();
            bs.release();
        }
    }
}


public class SecondExercise {
    public static void main(String[] args) {
        BinaryCounter bc = new BinaryCounter();
        Thread t1 = new Thread(new SecondExerciseRunnable(0,bc));
        Thread t2 = new Thread(new SecondExerciseRunnable(1, bc));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Counter is: " + bc.getCounter());
    }

}
