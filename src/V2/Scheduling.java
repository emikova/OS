package V2;
import java.util.*;
public class Scheduling {
    public static ArrayList<Process> processes = new ArrayList<Process>(Arrays.asList(
            new Process(1,10),
            new Process(2,5)
    ));

    public static void main(String[] args) {
        //JSF();
        RRScheduling(2);
        //PriorityScheduling();
    }

    static void JSF(){
        Queue<Process> queue = new PriorityQueue<>((p1,p2)->{
            return Float.compare(p1.burst_time,p2.burst_time);
        });

        for(Process p: processes){
            queue.add(p);
        }

        while (!queue.isEmpty()){
            Process current = queue.poll();
            System.out.println("Izvrsava se proces: " + current);
            try {
                Thread.sleep((int) (current.burst_time * 1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static void RRScheduling(int quantum){
        Queue<Process> queue = new LinkedList<>();

        for(Process p : processes){
            queue.add(p);
        }

        int numAdd = 1;
        while (!queue.isEmpty()){
            Process current = queue.poll();
            float runningTime = Math.min(quantum, current.remaining_time);
            System.out.println("Izvrsava se proces: " + current + "\t" + runningTime + "s");
            try {
                Thread.sleep((int)(runningTime*500));
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            current.remaining_time -= runningTime;

            if (numAdd > 0 ){
                numAdd--;
                processes = new ArrayList<Process>(Arrays.asList(
                        new Process(3,8),
                        new Process(4,4)
                ));
                for (Process p : processes){
                    queue.add(p);
                }
            }
            if (current.remaining_time > 0){
                queue.add(current);
            }

        }

    }

    static void PriorityScheduling(){
        Queue<Process> queue = new PriorityQueue<>((p1,p2)->{
            return Float.compare(p1.priority, p2.priority);
        });

        processes = new ArrayList<Process>(Arrays.asList(
                new Process(1,10,2),
                new Process(2,5,1),
                new Process(3,4,1)
        ));

        for (Process p: processes){
            queue.add(p);
        }

        while (!queue.isEmpty()){
            Process current = queue.poll();
            System.out.println("Izvrsava se proces: " + current);
            try {
                Thread.sleep((int) (current.burst_time*1000));
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

}
