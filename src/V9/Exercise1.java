package V9;
import java.util.LinkedList;
import java.util.Random;
import java.util.Queue;

public class Exercise1 {
    public static void FIFO_Simulation(int[] pages, int capacity){
        Queue<Integer> row = new LinkedList<>();
        int counter = 0;

        for(int page: pages){
            if (!row.contains(page)){
                if (row.size() == capacity){
                    int removed = row.poll();
                    System.out.println("Removing page " + removed + " from RAM");
                    counter ++;
                }
                row.offer(page);
                System.out.println("Page " + page + " is added to RAM");

            }
            System.out.println("Page " + page + " is on RAM");
        }
        System.out.println("Total number of swaps " + counter);
    }

    public static void main(String[] args) {
        Random random = new Random();
        //int[] pages = new int[100];
       /* for (int i = 0; i < 100; i++){
            pages[i] = random.nextInt(10);
        }*/
        int capacity = 5;
        int[] pages ={1,2,3,4,5,6,7,8,9,10};
        FIFO_Simulation(pages, capacity);
    }
}
