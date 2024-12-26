package SimulationOfOperatingSystemAndVirtualMachine;

public class Page {
    private int pageNumber;
    private String processId;
    private boolean inMemory;
    private long lastUsageTime;
    private byte[] data;

    public Page(int pageNumber, String processId){
        this.pageNumber = pageNumber;
        this.processId = processId;
        this.inMemory = false;
        this.lastUsageTime = System.currentTimeMillis();
        this.data = new byte[4096];
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public boolean isInMemory() {
        return inMemory;
    }

    public void setInMemory(boolean inMemory) {
        this.inMemory = inMemory;
    }

    public long getLastUsageTime() {
        return lastUsageTime;
    }

    public void setLastUsageTime(long lastUsageTime) {
        this.lastUsageTime = lastUsageTime;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "Page{" +
                "pageNumber=" + pageNumber +
                ", processId='" + processId + '\'' +
                ", inMemory=" + inMemory +
                ", lastUsageTime=" + lastUsageTime +
                '}';
    }

    public void updateLastUsedTime() {
        this.lastUsageTime = System.nanoTime();
    }
}
