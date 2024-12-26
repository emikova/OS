package Vjezbe2;

import java.util.ArrayList;

public class RoundRobin {

    public static int[] Find_Completion_Time(int arrival_time[], int burst_time[], int quantum){
        ArrayList<Integer> turn  = new ArrayList<>();
        int completion_time[] = new int[arrival_time.length];
        int copy_arrival_time[] = new int[arrival_time.length];
        int copy_burst_time[] = new int[arrival_time.length];
        int t = 0;

        for(int i = 0; i < arrival_time.length; i++){
            copy_arrival_time[i] = arrival_time[i];
            copy_burst_time[i] = burst_time[i];
        }

        for(int i = 0; i < arrival_time.length; i++){
            if(copy_arrival_time[i] <= t){
                turn.add(i);
                copy_arrival_time[i] = -1;
            }
        }

        while(!turn.isEmpty()){
            int p = turn.get(0);
            turn.remove(0);

            if(copy_burst_time[p] != 0){
                if(copy_burst_time[p] > quantum){
                    copy_burst_time[p] -= quantum;
                    t+= quantum;
                }else{
                    completion_time[p] += copy_burst_time[p] + t;
                    t += copy_burst_time[p];
                    copy_burst_time[p] = 0;
                }
            }
            for(int i = 0; i < arrival_time.length; i++){
                if(copy_arrival_time[i] <= t && copy_arrival_time[i] != -1){
                    turn.add(i);
                    copy_arrival_time[i] = -1;
                }
            }
            if(copy_burst_time[p] + quantum > quantum){
                turn.add(p);
            }
        }
        return completion_time;
    }


    public static int[] Find_Turnaround_Time(int completion_time[], int arrival_time[]){
        int turnaround_time[] = new int[arrival_time.length];

        for(int i = 0; i < arrival_time.length; i++){
            turnaround_time[i] = completion_time[i] - arrival_time[i];
        }

        return turnaround_time;
    }

    public static int[] Find_Waiting_Time(int turnaround_time[], int burst_time[]){
        int waiting_time[] = new int[turnaround_time.length];

        for(int i= 0; i< turnaround_time.length; i++){
            waiting_time[i] = turnaround_time[i] - burst_time[i];
        }

        return waiting_time;
    }

    public static void Find_Times(int arrival_time[], int burst_time[], int quantum){
        int completion_time[] = Find_Completion_Time(arrival_time, burst_time, quantum);
        int turnaround_time[] = Find_Turnaround_Time(completion_time, arrival_time);
        int waiting_time[] = Find_Waiting_Time(turnaround_time, burst_time);

        float sum_wt = 0, sum_tt = 0;

        for(int i = 0; i < arrival_time.length; i++){
            System.out.println(i + "\t" + arrival_time[i] + "\t" + burst_time[i] + "\t" + completion_time[i] + "\t" + turnaround_time[i] + "\t" + waiting_time[i]);
            sum_wt += waiting_time[i];
            sum_tt += turnaround_time[i];
        }

        System.out.println("Average waiting time: " + sum_wt/burst_time.length);
        System.out.println("Average turnaround time: " + sum_tt/burst_time.length);
    }


    public static void main(String[] args) {
        int arrival_time[] = {0,1,2,4};
        int burst_time[] = {5,4,2,1};

        Find_Times(arrival_time,burst_time,2);
    }


}
