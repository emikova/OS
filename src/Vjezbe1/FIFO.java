package Vjezbe1;

import javax.sound.midi.Soundbank;

public class FIFO {
    public static int[] Find_Waiting_Time(int burst_time[]){
        int waiting_time[] = new int[burst_time.length];
        for(int i=1; i<burst_time.length; i++){
            waiting_time[i] = waiting_time[i-1] + burst_time[i-1];
        }
        return waiting_time;
    }

    public static int[] Find_TurnAround_Time(int burst_time[], int waiting_time[]){
        int turnaround_time[] = new int[burst_time.length];
        for(int i=0; i<burst_time.length;i++){
            turnaround_time[i] = waiting_time[i] + burst_time[i];
        }
        return turnaround_time;
    }

    public static void Find_WT_TT(int burst_time[]){
        int waiting_time[] = Find_Waiting_Time(burst_time);
        int turnaround_time[] = Find_TurnAround_Time(burst_time, waiting_time);
        float sum_wt = 0, sum_tt = 0;

        for(int i = 0; i<burst_time.length; i++){
            System.out.println(i+ "\t" + burst_time[i] + "\t" + waiting_time[i] + "\t" + turnaround_time[i]);
            sum_wt += waiting_time[i];
            sum_tt += turnaround_time[i];
        }
        System.out.println("Average waiting time: " + sum_wt/burst_time.length);
        System.out.println("Average turnaround time: " + sum_tt/burst_time.length);

    }

    public static void main(String[] args) {
        int burst_time[] = {10,5,8};
        Find_WT_TT(burst_time);
    }
}
