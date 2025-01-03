package V5;

public class BinarySemaphore {
    private boolean signal = true;

    public synchronized void aquire() throws InterruptedException{
        while(!signal){
           wait();
        }
        signal = false;
    }

    public synchronized void release(){
        signal = true;
        notify();
    }
}
