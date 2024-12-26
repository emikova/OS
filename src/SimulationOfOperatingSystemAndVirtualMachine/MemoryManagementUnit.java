package SimulationOfOperatingSystemAndVirtualMachine;

public class MemoryManagementUnit {
    private final RAM ram;
    private final SSD ssd;

    public MemoryManagementUnit(RAM ram, SSD ssd){
        this.ram = ram;
        this.ssd = ssd;
    }

    public void initializeRAM(){
        System.out.println("Initializing RAM...");
        for (int i = 0; i < ram.getFrameCount(); i++){
            if (ssd.containsPage(i)){
                ram.loadPageIntoFrame(ssd.getPage(i));
            }
        }
        ram.printingFrames();
    }

    public void handlePageRequest(int pageNumber){
        if (ram.isAPageInRAM(pageNumber)){
            System.out.println("Page " + pageNumber + " is found in RAM!");
            Frame frame = ram.getFrameForThePage(pageNumber);
            frame.getPage().updateLastUsedTime();
        }else {
            System.out.println("Page " + pageNumber + " not found in RAM. Loasing from SSD...");
            if (ssd.containsPage(pageNumber)){
                Page p = ssd.getPage(pageNumber);
                p.updateLastUsedTime();
                ram.loadPageIntoFrame(p);
            }else {
                System.out.println("Page " + pageNumber + " doesn't exist!");
            }
        }

        ram.printingFrames();

    }

}
