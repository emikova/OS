package V6;

import java.util.Scanner;

class CPU extends Thread{
    private ProcessManager processManager;
    private float quantum;

    public CPU(ProcessManager processManager, float quantum){
        this.processManager = processManager;
        this.quantum = quantum;
    }

    @Override
    public void run(){
        while(!processManager.isEmpty()){
            Process process = processManager.nextProcess();
            if(process.getCode() != null && process.getCode().compareTo("ps") == 0){
                System.out.println(processManager);

            }
        }
    }
}

class OS{
    CPU cpu;
    ProcessManager processManager;
    private boolean power = false;
    private String cmd;
    public static volatile boolean waiting = false;

    OS(){
        power = true;
        processManager = new ProcessManager();
        processManager.basicProcesses();


        cpu = new CPU(processManager, 10);
        cpu.start();

        Scanner scanner = new Scanner(System.in);

        while (power){
            if (!waiting){
                System.out.println("cmd>");
                cmd = scanner.next();

            }else {
                cmd = "";

                switch (cmd){
                    case "exit":
                        power = false;
                        break;
                    case "ps":
                        processManager.addProcess(new Process(10,"ps"));
                        waiting = true;
                        break;
                    default:
                        break;
                }
            }
        }
        cpu.interrupt();
    }

}

public class ExerciseOne {
    public static void main(String[] args) {
        OS os = new OS();
    }
}
