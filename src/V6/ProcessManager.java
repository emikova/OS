package V6;

import java.util.LinkedList;
import java.util.Queue;

public class ProcessManager implements Scheduler {

    private Queue<Process> processManager;

    public ProcessManager(){
        processManager = new LinkedList<>();
    }

    public synchronized void addProcess(Process process){
        processManager.offer(process);
    }

    public void basicProcesses(){
        processManager.add(new Process(100));
        processManager.add(new Process(200));
        processManager.add(new Process(300));
        processManager.add(new Process(400));
        processManager.add(new Process(500));

    }

    public synchronized boolean isEmpty(){
        return processManager.isEmpty();
    }

    @Override
    public synchronized Process nextProcess(){
        return processManager.poll();
    }

    @Override
    public String toString() {
        String result = "";
        for (Process process: processManager) {
            result += process + "\n";
        }
        return result;
    }

}
