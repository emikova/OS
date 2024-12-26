package V3;

public class Z3Runnable implements Runnable{
    int name;
    Counter c;
    static boolean flag[] = new boolean[2];
    static int turn = 0;

    public Z3Runnable(int name, Counter c){
        this.name = name;
        this.c = c;
    }

    @Override
    public void run(){
        for (int i = 0; i < 10000; i++){
            flag[name] = true;
            int other_thread = (name + 1)% 2;
            turn = other_thread;
            while (flag[other_thread] && turn == other_thread){
                Thread.yield();
            }
            c.increment();
            flag[name] = false;

        }
    }
}
