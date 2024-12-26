package V3;

public class Example1{
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread("One"));
        Thread t2  = new Thread(new MyThread("Two"));

        t1.start();
        t2.start();

        System.out.println("I'm a 'main' thread");

        for (int i = 0; i < 100; i++){
            i++;
        }
        System.out.println("For loop just ended!");
    }
}

