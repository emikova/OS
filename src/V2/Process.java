package V2;
import java.util.*;

public class Process {
    int id;
    float burst_time;
    float remaining_time;
    int priority = 0;

    Process(int id, float burst_time){
        this.id = id;
        this.burst_time = burst_time;
        this.remaining_time = burst_time;
    }

    Process(int id, float burst_time, int priority){
        this.id = id;
        this.burst_time = burst_time;
        this.remaining_time = burst_time;
        this.priority = priority;
    }

    @Override
    public String toString(){
        return String.valueOf(id);
    }

}
