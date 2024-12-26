package V7;

import java.util.ArrayList;

public class MemoryManagerTwo {
    private int total;
    private ArrayList<MemoryBlock> memory;

    public MemoryManagerTwo(int total){
        this.total = total;
        memory = new ArrayList<MemoryBlock>();
        memory.add(new MemoryBlock(0, total, false));
    }

    public void allocate(int size){
        for(MemoryBlock mb : memory){
            if (!mb.isAllocated() && mb.getSize() >= size){
                mb.setAllocated(true);

                if (mb.getSize() > size){
                    memory.add(new MemoryBlock(mb.getAddress() + size, mb.getSize() - size, false));
                    mb.setSize(size);
                }
                return ;

            }
        }
        System.out.println("Couldn't allocate the memory! :'( ");

    }

    public void deallocate(int address){
        for (MemoryBlock mb: memory){
            if (mb.getAddress() == address){
                mb.setAllocated(false);
                MergeMemory();
                return;
            }
        }
        System.out.println("It's not possible to deallocate the memory on given address!");
    }

    public void MergeMemory(){
        for (int i = 0; i < memory.size()-1; i++){
            MemoryBlock memoryBlockOne = memory.get(i);
            MemoryBlock memoryBlockTwo = memory.get(i+1);
            if (!memoryBlockOne.isAllocated() && !memoryBlockTwo.isAllocated()){
                MemoryBlock newOne = new MemoryBlock(memoryBlockOne.getAddress(), memoryBlockOne.getSize() + memoryBlockTwo.getSize(),false);
                memory.add(i, newOne);

                memory.remove(i+1);
                memory.remove(i+1);

                i--;
            }
        }
    }


    public void resize(int address, int new_size){
        for (MemoryBlock memoryBlock : memory){
            int current_size = memoryBlock.getSize();
            int rest = current_size-new_size;
            if (memoryBlock.getAddress() == address && memoryBlock.isAllocated()){
                MemoryBlock rightNeighbor = findRightNeighbor(address,current_size);
                MemoryBlock leftNeighbor = findLeftNeighbor(address);
                if (new_size < memoryBlock.getSize()){
                    if(!rightNeighbor.isAllocated() && rightNeighbor != null){
                        memoryBlock.setSize(new_size);
                        rightNeighbor.setAddress(rightNeighbor.getAddress() - rest);
                        rightNeighbor.setSize(rightNeighbor.getSize() + rest);
                    }else if(!leftNeighbor.isAllocated() && leftNeighbor != null){
                        memoryBlock.setSize(new_size);
                        leftNeighbor.setSize(leftNeighbor.getSize() + rest);
                        memoryBlock.setAddress(leftNeighbor.getSize());

                    }else {
                        System.out.println("It's impossible to change the size of a memory you required!");
                        System.out.println("Memories with the previous and the next addresses aren't deallocated! ");

                    }
                }else {
                    int required = new_size - current_size;
                    if (!rightNeighbor.isAllocated() && rightNeighbor != null && rightNeighbor.getSize() >= required) {
                        memoryBlock.setSize(current_size + required);
                        rightNeighbor.setAddress(rightNeighbor.getAddress() + required);
                        rightNeighbor.setSize(rightNeighbor.getSize() - required);
                    } else if (!leftNeighbor.isAllocated() && leftNeighbor != null && leftNeighbor.getSize() >= required) {
                        memoryBlock.setSize(current_size + required);
                        leftNeighbor.setSize(leftNeighbor.getSize() - required);
                        memoryBlock.setAddress(address - required);

                    }else {
                        System.out.println("It's not possible to change the size of a memory you required!");
                        System.out.println("Memories with the previous and the next addresses aren't deallocated! ");
                    }
                }
            }
        }
    }

    public MemoryBlock findLeftNeighbor(int address){
        for (MemoryBlock mb: memory){
            if (mb.getAddress() + mb.getSize() == address){
                return mb;
            }
        }
        return null;
    }

    public MemoryBlock findRightNeighbor(int address, int size){
        for (MemoryBlock mb: memory){
            if (mb.getAddress() == address + size){
                return mb;
            }
        }
        return null;
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

class Exercise2{
    public static void main(String[] args) {
        MemoryManagerTwo memoryManagerTwo = new MemoryManagerTwo(1024);
        memoryManagerTwo.allocate(120);
        memoryManagerTwo.allocate(34);
        memoryManagerTwo.allocate(40);
        memoryManagerTwo.allocate(23);
        memoryManagerTwo.allocate(507);

        memoryManagerTwo.deallocate(154);
        memoryManagerTwo.deallocate(0);
        memoryManagerTwo.resize(120,20);
        System.out.println(memoryManagerTwo);
    }
}
