package V7;

import java.util.ArrayList;

public class MemoryManager {
    private int total;
    private ArrayList<MemoryBlock> memory;

    public MemoryManager(int total, int size_block){
        this.total = total;
        this.memory = new ArrayList<MemoryBlock>();

        for (int i = 0; i < total; i+= size_block){
            memory.add(new MemoryBlock(i,size_block,false));
        }
    }

    public boolean allocate(int size){
        for (MemoryBlock mb: memory){
            if(!mb.isAllocated() && mb.getSize() >= size){
                mb.setAllocated(true);
                return true;
            }
        }
        return false;
    }

    public boolean deallocate(int address){
        for (MemoryBlock mb: memory){
            if (mb.getAddress() == address){
                if (!mb.isAllocated()){
                    System.out.println("Your memory block is free! :)");
                }else {
                    mb.setAllocated(false);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "Stanje memorije: \n";

        for (MemoryBlock mb: memory) {
            result += mb + "\n";
        }

        return result;
    }
}
class Exercise1{
    public static void main(String[] args) {
        MemoryManager memoryManager = new MemoryManager(100,20);

        boolean status;
        status = memoryManager.allocate(15);
        System.out.println(status);
        status = memoryManager.allocate(20);
        System.out.println(status);
        status = memoryManager.allocate(10);
        System.out.println(status);
        status = memoryManager.deallocate(20);
        System.out.println(status);
        status = memoryManager.deallocate(60);
        System.out.println(status);
        System.out.println(memoryManager);
    }
}
