package Vjezbe5;
import java.util.Random;

public class Client2 implements Runnable{
    private Random rand = new Random();
    private String name;
    private int account_id;
    private int balance;
    private Bank2 bank;

    public Client2(String name, int account_id, int balance, Bank2 bank){
        super();
        this.name = name;
        this.account_id = account_id;
        this.balance = balance;
        this.bank = bank;

    }

    @Override
    public void run() {
        int to;
        int amount;
        while (true) {
            to = rand.nextInt(bank.getClientCount());
            amount = 0;
            if (balance > 0) {
                amount = rand.nextInt(getBalance()) + 1;
                bank.transfer(account_id, to, amount);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getBalance(){
        return balance;
    }

    public void addToBalance(int amount){
        balance += amount;
    }

    public int getAccount_id(){
        return account_id;
    }
}
