package V4;
  public class Counter {
      private int counter = 0;

      public void increment(){
          counter++;
      }

      public int getCounter(){
          return counter;
      }
  }
  class Example1 implements Runnable{
      int thread_id;
      Counter c;

      static boolean[] flag = new boolean[2];
      int turn = 0;

      public Example1(int thread_id, Counter c){
          this.thread_id = thread_id;
          this.c = c;
      }

      @Override
      public void run(){
          for(int i = 0; i < 100; i++){
              flag[thread_id] = true;
              int other_thread = (thread_id + 1) % 2;
              while (flag[other_thread]){
                  if (turn == other_thread){
                      flag[thread_id] = false;
                      while(turn == other_thread){
                          Thread.yield();
                          System.out.println("Thread " + thread_id + " ceka");
                      }
                      flag[thread_id] = true;
                  }

              }
              System.out.println("Thread " + thread_id + " je u kriticnoj sekciji!");
              c.increment();
              flag[thread_id] = false;
              turn = other_thread;


          }
      }


      public static void main(String[] args) {
          Counter counter = new Counter();
          Thread t1 = new Thread(new Example1(0,counter ));
          Thread t2 = new Thread(new Example1(1, counter));

          t1.start();
          t2.start();

          try {
              t1.join();
              t2.join();
          }catch (InterruptedException e){
              e.printStackTrace();
          }
          System.out.println("Brojac : " + counter.getCounter());
      }
  }


