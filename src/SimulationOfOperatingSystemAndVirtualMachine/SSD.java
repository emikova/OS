package SimulationOfOperatingSystemAndVirtualMachine;

import java.util.HashMap;
import java.util.Map;

public class SSD {
    private final Map<Integer, Page> storage;

    public SSD(int totalPages){
        this.storage = new HashMap<>();
        for (int i = 0; i < totalPages; i++){
            storage.put(i,new Page(i, "ProcessX"));
        }
    }

    public Page getPage(int pageNumber){
        return storage.get(pageNumber);
    }

    public boolean containsPage(int pageNumber){
        return storage.containsKey(pageNumber);
    }

}
