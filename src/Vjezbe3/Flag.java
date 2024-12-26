package Vjezbe3;

import java.security.spec.ECField;
import java.util.SortedMap;

public class Flag {
    int A = 10, counter = 0;
    boolean flag[] = new boolean[2];
    public static int tcs;

    private class Thread1 extends Thread{
        @Override
        public void run(){
            try {
                do {
                    flag[0] = true;
                    Flag.tcs = 0;
                    while (flag[1] && Flag.tcs == 0){
                        Thread.sleep(1);
                    }

                    synchronized (Flag.this){
                        if(counter<100){
                            System.out.println("1st Thread entering the Critical Section");
                            A++;
                            System.out.println(A);
                            counter++;
                            System.out.println("Counter is " + counter + "\n_____________");
                        }
                    }
                    flag[0] = false;
                }while(counter<100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    private class Thread2 extends Thread{
        @Override
        public void run(){
            try {
                do {
                    flag[1] = true;
                    Flag.tcs = 1;

                    while (flag[0] && Flag.tcs == 1){
                        Thread.sleep(1);
                    }

                    synchronized (Flag.this){
                        if(counter<100){
                            System.out.println("2nd Thread entering the Critical Section");
                            A--;
                            System.out.println(A);
                            counter ++;
                            System.out.println("Counter is " + counter + "\n______________");
                        }
                    }
                    flag[1] = false;
                }while (counter < 100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public Flag(){
        flag[0] = false;
        flag[1] = false;
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        Flag f = new Flag();
    }
}
