package V1;

public class Zadatak1 {
    public static void main(String[] args) {
        int n = 3;
        int burst_time[] ={4,3,1};
        int completion_time[] = {0,0,0};
        int turnaround_time[] = {0,0,0};
        int waiting_time[] = {0,0,0};

        completion_time[0] = burst_time[0];

        for(int i = 1; i < n; i++){
            completion_time[i] = completion_time[i-1] + burst_time[i];
        }

        for (int i = 0; i < n; i++){
            turnaround_time[i] = completion_time[i];
        }

        for (int i = 0 ; i < n; i++){
            waiting_time[i] = turnaround_time[i] - burst_time[i];
        }

        System.out.println("Processes \tBT \tCT \tTT \tWT:");
        for (int i = 0; i < n; i++){
            System.out.println(burst_time[i] + "\t" + completion_time[i] + "\t" + turnaround_time[i] + "\t" + waiting_time[i]);

        }
    }


}
