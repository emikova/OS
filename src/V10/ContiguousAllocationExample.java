package V10;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;

class File {
    private String name;
    private int startBlock;
    private int length;
    private String content;
    public static final int size = 4;

    public File(String name, int startBlock, int length) {
        this.name = name;
        this.startBlock = startBlock;
        this.length = length;
    }

    public File(String name, int startBlock, int length, String content) {
        this.name = name;
        this.startBlock = startBlock;
        this.length = length;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public int getStartBlock() {
        return startBlock;
    }

    public int getLength() {
        return length;
    }

    public String getContent() {
        return content;
    }
}
class ContiguousAllocation {
    private static final int TOTAL_BLOCKS = 100;
    private boolean[] allocatedBlocks;
    private Map<String, File> fileTable;

    public ContiguousAllocation() {
        allocatedBlocks = new boolean[TOTAL_BLOCKS];
        fileTable = new HashMap<>();
    }

    public boolean allocateFile(String fileName, int lenght) {
        for (int i = 0; i < TOTAL_BLOCKS - lenght + 1; i++) {
            boolean isFree = true;
            for (int j = i; j < i + lenght; j++) {
                if (allocatedBlocks[j]) {
                    isFree = false;
                    break;
                }
            }
            if (isFree) {
                File newFile = new File(fileName, i, lenght);
                fileTable.put(fileName, newFile);

                for (int j = i; j < i + lenght; j++) {
                    allocatedBlocks[j] = true;
                }
                System.out.println("File " + fileName + " allocate starting from block " + i);
                return true;
            }
        }
        System.out.println("Not enought contiguous blocks available for file " + fileName);
        return false;
    }
    public boolean allocateFile(String fileName, int length, String content) {
        for (int i = 0; i < TOTAL_BLOCKS - length + 1; i++) {
            boolean isFree = true;
            for (int j = i; j < i + length; j++) {
                if (allocatedBlocks[j]) {
                    isFree = false;
                    break;
                }
            }
            if (isFree) {
                String block = content.substring(0, File.size);
                File newFile = new File(fileName, i, length, block);
                fileTable.put(fileName, newFile);
                for (int j = i; j < i + length; j++) {
                    allocatedBlocks[j] = true;
                    if (i != j) {
                        fileTable.put("" + j, new File(null, j, 0, content.substring(File.size * (j - i), File.size * (j - i) + File.size)));
                    }
                }
                System.out.println("File " + fileName + " allocated starting from block " + i);
                return true;
            }
        }
        System.out.println("Not enought contiguous blocks available for file " + fileName);
        return false;
    }
    public void deallocateFile(String fileName) {
        if (fileTable.containsKey(fileName)) {
            File file = fileTable.get(fileName);
            for (int i = file.getStartBlock(); i < file.getStartBlock() + file.getLength(); i++) {
                allocatedBlocks[i] = false;
            }
            System.out.println("File " + fileName + " deallocated!");
            fileTable.remove(fileName);
        } else {
            System.out.println("File " + fileName + " not found!");
        }
    }
    public void displayDiskStatus() {
        System.out.println("Disk status: ");
        for (int i = 0; i < TOTAL_BLOCKS; i++) {
            if (allocatedBlocks[i]) {
                System.out.println("X ");
                for (File file : fileTable.values()) {
                    if (file.getStartBlock() == i) {
                        System.out.println(file.getContent());
                    }
                }
            } else {
                System.out.println("___");
            }
        }
        System.out.println();
    }
}

public class ContiguousAllocationExample {
    public static void main(String[] args) {
        ContiguousAllocation fileSystem = new ContiguousAllocation();
        fileSystem.displayDiskStatus();

        fileSystem.allocateFile("file1", 4, "Danas je divan d");
        fileSystem.displayDiskStatus();

        fileSystem.allocateFile("file2", 2);
        fileSystem.displayDiskStatus();

        fileSystem.deallocateFile("file1");
        fileSystem.displayDiskStatus();

        fileSystem.allocateFile("file3", 3);
        fileSystem.displayDiskStatus();

        fileSystem.allocateFile("file5", 1);
        fileSystem.displayDiskStatus();
    }

}

