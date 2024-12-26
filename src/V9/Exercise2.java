package V9;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.SortedMap;

public class Exercise2 {
    public static void LRU_Simulation(int[] pages, int capacity){
        Queue<Integer> row = new LinkedList<>();
        int counter = 0;

        for(int page : pages){
            if (!row.contains(page)){
                if (row.size() == capacity){
                    int removed = row.poll();
                    System.out.println("Page " + removed + " has been removed from RAM!");
                    counter ++;
                }
                row.offer(page);
                System.out.println("Putting " + page + " on the RAM");
            }else {
                System.out.println("Page is on the RAM");
                row.remove((Integer) page);
                row.offer(page);
                System.out.println(row);

            }
        }
        System.out.println("Total count of swaps " + counter);
    }

    public static void main(String[] args) {
        Random random = new Random();
        int [] pages = {1, 2, 3, 4, 5, 1, 2, 6};
        int capacity = 5;
        LRU_Simulation(pages, capacity);
    }
}
