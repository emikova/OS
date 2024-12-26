package V5;

class Lift{
    private CountingSemaphore countingSemaphore = new CountingSemaphore(3);

    public void entry(int name) throws InterruptedException{
        System.out.println("Person with the name  " + name + " is waiting to enter the lift...");
        countingSemaphore.aquire();
        System.out.println("Person " + name + "just entered the lift...");

    }

    public void exit(int name){
        countingSemaphore.release();
        System.out.println("Person with the name " + name + " just exited the lift....");
    }
}

class Person extends Thread{
    private int name;
    Lift lift;

    public Person(int name, Lift lift){
        this.name = name;
        this.lift = lift;
    }

    @Override
    public void run(){
        try {
            lift.entry(name);
            Thread.sleep(500);
        }catch (InterruptedException e){
            throw new RuntimeException();
        }
        lift.exit(name);
    }
}


public class ThirdExerciseLift {
    public static void main(String[] args) {
        Lift lift = new Lift();
        for(int i = 0; i < 10; i++){
            Person person = new Person(i, lift);
            person.start();
        }
    }
}
