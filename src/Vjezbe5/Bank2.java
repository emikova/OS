package Vjezbe5;
import java.lang.ref.Cleaner;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;
public class Bank2 {
    private Random rand = new Random();
    private Client2[] clients;
    private String[] names = new String[]{"Peter","Joe", "Quagmire","Cleveland"};
    private ReentrantLock lock;

    public Bank2(int n, int initialAmmount){
        clients = new Client2[n];
        for(int i = 0; i < n; i++){
            clients[i] = new Client2(names[i],i,initialAmmount,this );
        }

        for(int i = 0; i < n; i++){
            new Thread(clients[i]).start();
        }
    }

    void transfer(int from, int to, int amount){
        clients[from].addToBalance(-amount);
        clients[to].addToBalance(amount);
        System.out.println("Total balance: " + total());
    }

    private int total(){
        int sum = 0;
        for(Client2 c: clients){
            sum+=c.getBalance();
        }
        return sum;
    }

    public int getClientCount(){
        return clients.length;
    }


    public static void main(String[] args) {
        Bank2 bank = new Bank2(4,100);

    }
}
