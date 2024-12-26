package Vjezbe5;
import java.util.Random;

public class Bank1 {

    private Random rand = new Random();
    private String names[] = new String[]{"Peter", "Quagmire","Joe", "Cleveland"};
    private Client1 clients[];

    public Bank1(int n, int initialbalance){
        clients = new Client1[n];
        for(int i = 0; i < n ; i++){
            clients[i] = new Client1(names[i], i, initialbalance, this );
        }

        for(int i = 0; i < n; i++){
            new Thread(clients[i]).start();
        }
    }
    public int getClientCount(){
        return clients.length;
    }

    public int totalBalance(){
        int sum = 0;
        for(Client1 c: clients){
            sum += c.getBalance();
        }
        return sum;
    }


    void transfer(int from, int to, int amount){
        clients[from].addToBalance(-amount);
        clients[to].addToBalance(amount);
        System.out.println("Total balance is : " + totalBalance());
    }

    public static void main(String[] args) {
        Bank1 bank = new Bank1(4,20);
    }



}
