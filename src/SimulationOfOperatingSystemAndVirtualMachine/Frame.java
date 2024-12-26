package SimulationOfOperatingSystemAndVirtualMachine;

public class Frame {
    private final int frameId;
    private Page page;

    public Frame(int frameId){
        this.frameId = frameId;
        this.page = null;
    }

    public int getFrameId() {
        return frameId;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
