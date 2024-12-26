package V6;
import java.util.Random;



class Client implements Runnable{
    Random random = new Random();
    private String name;
    private int account_Id;
    private int balance;
    private BankHomework bank;

    public Client(String name, int account_Id, int balance,BankHomework bank){
        this.name = name;
        this.account_Id = account_Id;
        this.balance = balance;
        this.bank = bank;
    }


    @Override
    public void run(){
        int to;
        int amount;
        while (true){
            to = random.nextInt(bank.getClientCount());
            amount = 0;
            if (balance>0){
                amount = random.nextInt(balance) + 1;
                bank.transfer(account_Id,to,amount);
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }

        }
    }


    public void addToBalance(int amount){
        balance += amount;
    }

    public int getBalance(){
        return balance;
    }
}
public class BankHomework {
    private Random random = new Random();
    private String[] names = new String[]{"Joe", "Quagmire","Peter","Cleveland"};
    private Client[] clients;

    public BankHomework(int n, int initialBalance){
        clients = new Client[n];
        for (int i = 0; i < n; i++){
            clients[i] = new Client(names[i],i,initialBalance,this);
        }
        for (int i = 0; i < n; i++){
            new Thread(clients[i]).start();
        }
    }

    void transfer(int from, int to, int amount){
        clients[from].addToBalance(-amount);
        clients[to].addToBalance(amount);
        System.out.println(total());
    }

    private int total(){
        int sum = 0;
        for (Client client:clients){
            sum+=client.getBalance();
        }
        return sum;
    }

    public int getClientCount(){
        return clients.length;
    }

    public static void main(String[] args) {
        BankHomework bank = new BankHomework(4,10);
    }

}
