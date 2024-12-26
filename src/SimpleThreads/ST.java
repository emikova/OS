package SimpleThreads;

import java.text.NumberFormat;

public class ST {
    public static void ThreadMessage(String message){
        String threadName = Thread.currentThread().getName();
        System.out.format("%s : %s%n", threadName, message);
    }

    public static class MessageLoop implements Runnable{
        public void run() {
            String importantInfo[] = {
                    "You cheating son of a bitch, you're supposed to say uno",
                    "I said one",
                    "You're supposed to say uno, it's a Mexican game"
            };
            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    Thread.sleep(2000);
                    ThreadMessage(importantInfo[i]);
                }
            } catch (InterruptedException e) {
                ThreadMessage("I wasn't done!");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        long patience = 1000*60*60;
        if(args.length > 0){
            try {
                patience = Long.parseLong(args[0]) * 1000;
            }catch (NumberFormatException e){
                System.out.println("Number must be integer!");
                System.exit(1);
            }
        }
        ThreadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();

        ThreadMessage("Waiting for a MessageLoop thread to finish");

        while (t.isAlive()){
            ThreadMessage("Still waiting....");

            t.join(500);

            if (((System.currentTimeMillis()) - startTime) > patience && t.isAlive()){
                ThreadMessage("Tired of waiting!");
                t.interrupt();

                t.join();

            }

        }
        ThreadMessage("Finally");
    }
}
