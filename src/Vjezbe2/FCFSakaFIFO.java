package Vjezbe2;

public class FCFSakaFIFO {

    public static int[] Find_Waiting_Time(int burst_time[], int arrival_time[]){
        int waiting_time[] = new int[burst_time.length];
        int completion_time[] = new int[burst_time.length];

        for(int i = 0; i < burst_time.length; i ++){
            if(i == 0){
                completion_time[i] = arrival_time[i] + burst_time[i];
            }else{
                completion_time[i] = Math.max(completion_time[i-1], arrival_time[i]) + burst_time[i];
            }
        }

        for(int i= 0; i < burst_time.length; i++){
            waiting_time[i] = completion_time[i] - burst_time[i] - arrival_time[i];
        }
        return waiting_time;
    }

    public static int[] Find_TurnaroundTime(int burst_time[], int waiting_time[]){
        int turnaround_time[] = new int[burst_time.length];
        for(int i = 0; i < burst_time.length; i++){
            turnaround_time[i] = waiting_time[i] + burst_time[i];
        }

        return turnaround_time;
    }

    public static void Find_Average_WT_TT(int burst_time[], int arrival_time[]){
        int waiting_time[] = Find_Waiting_Time(burst_time, arrival_time);
        int turnaround_time[] = Find_TurnaroundTime(burst_time,waiting_time);
        int completion_time[] = new int[burst_time.length];

        float sum_wt = 0, sum_tt = 0;

        for(int i = 0; i < burst_time.length; i++){
            if(i == 0){
                completion_time[i] = arrival_time[i] + burst_time[i];
            }else{
                completion_time[i] = Math.max(completion_time[i-1], arrival_time[i]) + burst_time[i];
            }
        }

        for(int i = 0; i < burst_time.length; i++){
            System.out.println(i + "\t" + burst_time[i] + "\t" + arrival_time[i] + "\t" + waiting_time[i] + "\t" + turnaround_time[i] + "\t" + completion_time[i]);
            sum_wt += waiting_time[i];
            sum_tt += turnaround_time[i];
        }

        System.out.println("Average waiting time: " + sum_wt/burst_time.length);
        System.out.println("Average turnaround time: " + sum_tt/burst_time.length);

    }

    public static void main(String[] args) {
        int burst_time[] = {5,4,2,1};
        int arrival_time[] = {0,1,2,4};

        Find_Average_WT_TT(burst_time, arrival_time);
    }
}
