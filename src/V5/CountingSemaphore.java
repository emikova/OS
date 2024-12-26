package V5;
import java.io.Closeable;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class CountingSemaphore {
    private int signals = 0;
    private int bound;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();


    public CountingSemaphore(int bound){
        if(bound >= 0){
            this.bound = bound;
        }else {
            throw new IllegalArgumentException("Permits mustn't be negative number!");
        }
    }

    public synchronized void aquire() throws InterruptedException{
        lock.lock();
        try {
            while (signals >= bound){
                notFull.await();
            }
            signals++;
            System.out.println("Current count after aquire :  " + signals);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }


    }

    public synchronized void release(){
        lock.lock();
        try {
            if(signals > 0){
                signals--;
                System.out.println("Current count after release:  " + signals);
                notFull.signalAll();
            }
        }finally {
            lock.unlock();
        }

    }
}
