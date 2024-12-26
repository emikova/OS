package V3;

public class Counter {
    private int counter = 0;

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}

class Smece implements Runnable {
    private String name;
    private Counter counter;

    public Smece(String name, Counter counter) {
        this.name = name;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            counter.increment();
        }
    }
}
class Z{
    public static void main(String[] args) {
        Counter c = new Counter();
        Thread t1 = new Thread(new Smece("t1", c));
        Thread t2 = new Thread(new Smece("t2", c));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(c.getCounter());
    }
}




