package V6;

public class Process {
    private static volatile int counter = 0;
    private int process_id;
    ProcessState processState;
    private float burst_time;
    private float remaining_time;
    private String code;

    public Process(float burst_time){
        this.process_id = counter++;
        this.burst_time = burst_time;
        this.remaining_time = burst_time;
        this.processState = ProcessState.NEW;

    }

    public Process(float burst_time, String code){
        this(burst_time);
        this.code = code;
    }

    @Override
    public String toString() {
        return "Proces " + process_id + " preostalo: " + remaining_time;
    }


    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Process.counter = counter;
    }

    public int getProcess_id() {
        return process_id;
    }

    public void setProcess_id(int process_id) {
        this.process_id = process_id;
    }

    public ProcessState getProcessState() {
        return processState;
    }

    public void setProcessState(ProcessState processState) {
        this.processState = processState;
    }

    public float getBurst_time() {
        return burst_time;
    }

    public void setBurst_time(float burst_time) {
        this.burst_time = burst_time;
    }

    public float getRemaining_time() {
        return remaining_time;
    }

    public void setRemaining_time(float remaining_time) {
        this.remaining_time = remaining_time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
