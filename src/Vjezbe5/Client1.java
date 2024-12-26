package Vjezbe5;
import java.util.Random;
import java.lang.Runnable;
public class Client1 implements Runnable{
    private String name;
    private int client_id;
    private int balance;
    private Bank1 bank;

    private Random rand = new Random();

    public Client1(String name, int client_id, int balance, Bank1 bank){
        super();
        this.name = name;
        this.client_id = client_id;
        this.balance = balance;
        this.bank = bank;
    }



    public int getBalance(){
        return balance;
    }

    public int getClient_id(){
        return client_id;
    }

    @Override
    public void run() {
        int to;
        int amount;
        while(true){
            to = rand.nextInt(bank.getClientCount());
            amount = 0;
            if(balance>0){
                amount = rand.nextInt(getBalance()) + 1;
                bank.transfer(client_id, to, amount);
                try {
                    Thread.sleep(300);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        }
    }

    public int addToBalance(int amount){
        return balance += amount;
    }
}
