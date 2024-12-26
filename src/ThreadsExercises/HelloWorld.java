package ThreadsExercises;

public class HelloWorld extends Thread{
    public void run(){
        System.out.println("Hello World!");
    }

    public static void main(String[] args) {
        (new HelloWorld()).start();
    }
}
