package V3;

public class Zadatak3 {
    public static void main(String[] args) {
        Counter c = new Counter();
        Thread t1 = new Thread(new Z3Runnable(0,c));
        Thread t2 = new Thread(new Z3Runnable(1,c));

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
