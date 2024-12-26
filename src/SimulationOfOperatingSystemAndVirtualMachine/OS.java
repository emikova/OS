package SimulationOfOperatingSystemAndVirtualMachine;

public class OS {
    public static void main(String[] args) {
        RAM ram = new RAM(3);
        SSD ssd = new SSD(10);
        MemoryManagementUnit mmu = new MemoryManagementUnit(ram,ssd);


        mmu.initializeRAM();
        CPU cpu = new CPU(mmu);

        cpu.accessPage(1);
        cpu.accessPage(2);
        cpu.accessPage(3);
        cpu.accessPage(1);
        cpu.accessPage(4);
    }
}
