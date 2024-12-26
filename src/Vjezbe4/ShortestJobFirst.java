package Vjezbe4;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

public class ShortestJobFirst {

    static class Job{
        int arrival_time;
        int burst_time;
        public Job(int arrival_time, int burst_time){
            this.arrival_time = arrival_time;
            this.burst_time = burst_time;
        }
    }

    public static void sorted(Job jobs[]){
        for(int i = 0; i < jobs.length-1; i++){
            for(int j = 0; j < jobs.length-1; j++){
                if((jobs[j].burst_time + jobs[j].arrival_time) > (jobs[j+1].burst_time + jobs[j+1].arrival_time)){
                    Job tmp = jobs[j];
                    jobs[j] = jobs[j+1];
                    jobs[j+1] = tmp;
                }
            }
        }
    }

    public static int[] Find_Waiting_Time(Job jobs[]){
        int waiting_time[] = new int[jobs.length];
        int completion_time[] = new int[jobs.length];
        for(int i = 0; i < jobs.length; i++){
            if(i==0){
                completion_time[i] = jobs[i].arrival_time + jobs[i].burst_time;
            }else{
                completion_time[i] = Math.max(completion_time[i-1], jobs[i].arrival_time) + jobs[i].burst_time;
            }
        }

        for(int i = 0; i < jobs.length; i++){
            waiting_time[i] = completion_time[i] - jobs[i].arrival_time - jobs[i].burst_time;
        }

        return waiting_time;

    }

    public static int[] Find_Turnaround_Time(Job jobs[], int waiting_time[]){
        int turnaround_time[] = new int[jobs.length];
        for(int i = 0; i < jobs.length; i++){
            turnaround_time[i] = jobs[i].burst_time + waiting_time[i];
        }
        return turnaround_time;
    }

    public static void Find_WT_TT (Job jobs[]){
        int waiting_time[] = Find_Waiting_Time(jobs);
        int turnaround_time[] = Find_Turnaround_Time(jobs,waiting_time);
        int completion_time[] = new int[jobs.length];

        float sum_wt = 0, sum_tt = 0;

        for(int i = 0; i < jobs.length; i++){
            if (i == 0){
                completion_time[i] = jobs[i].arrival_time + jobs[i].burst_time;
            }else{
                completion_time[i] = Math.max(completion_time[i-1], jobs[i].arrival_time) + jobs[i].burst_time;
            }
        }

        for(int i = 0; i < jobs.length; i++){
            System.out.println(i + "burst time : " + jobs[i].burst_time + ", arrival time: " + jobs[i].arrival_time + " waiting time: " + waiting_time[i] + " , turnaround time: " + turnaround_time[i] + " , completion time: " + completion_time[i]);
            sum_wt += waiting_time[i];
            sum_tt += turnaround_time[i];
        }


        System.out.println("Average waiting time: " + sum_wt/jobs.length);
        System.out.println("Average turnaround time: " + sum_tt/ jobs.length);
    }




    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n;
        System.out.println("Enter the lenght of burst array: ");
        n = scan.nextInt();
        int burst_time[] = new int[n];
        int arrival_time[] = new int[n];
        Job jobs[] = new Job[n];
        for(int i = 0; i < n; i++){
            System.out.println("Enter the burst time: ");
            burst_time[i] = scan.nextInt();
            System.out.println("Enter the arrival time:");
            arrival_time[i] = scan.nextInt();
            jobs[i] = new Job(arrival_time[i], burst_time[i]);
        }

        sorted(jobs);
        Find_WT_TT(jobs);
    }
}

//IMPROVE THE CODE
