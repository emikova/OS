package Vjezbe3;

import java.util.SortedMap;

public class CriticalSection{
    int turn;
    boolean flag[] = new boolean[2];
    int counter = 0;
    int cscVar = 13;
    int i = 0, j = 1;

    private class iTh extends Thread{
        public void run(){
            try {
                do {
                    flag[i] = true;
                    turn = j;
                    while (flag[j] && turn == j){
                        Thread.sleep(1);
                    }
                    synchronized (CriticalSection.this){
                        if(counter<100){
                            System.out.println("I is in the critical section");
                            cscVar ++;
                            System.out.println(cscVar);
                            counter ++;
                            System.out.println("Counter is " + counter + "\n ________________");
                        }
                    }

                    flag[i] = false;
                }while (counter<100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class jTh extends Thread{
        public void run(){
            try {
                do {
                    flag[j] = true;
                    turn = i;
                    while (flag[i] && turn == i){
                        Thread.sleep(1);
                    }
                    synchronized (CriticalSection.this){
                        if(counter<100){
                            System.out.println("J is in the critical section");
                            cscVar--;
                            System.out.println(cscVar);
                            counter++;
                            System.out.println("Counter is " + counter + "\n__________________");
                        }
                    }

                    flag[j] = false;

                }while (counter<100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public CriticalSection(){
        System.out.println("Starting Threads/Processes");
        Thread I = new iTh();
        Thread J = new jTh();

        I.start();
        J.start();
    }

    public static void main(String[] args) {
        CriticalSection cs = new CriticalSection();
    }
}
