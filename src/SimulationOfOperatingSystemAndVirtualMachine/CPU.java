package SimulationOfOperatingSystemAndVirtualMachine;

public class CPU {
    private final MemoryManagementUnit mmu;

    public CPU ( MemoryManagementUnit mmu){
        this.mmu = mmu;
    }

    public void accessPage(int pageNumber){
        System.out.println("CPU is requesting a page: " + pageNumber);
        mmu.handlePageRequest(pageNumber);
    }
}

