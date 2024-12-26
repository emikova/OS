package Vjezbe4;
import java.util.Random;
public class Race {
    public static void main(String[] args) {
        Object[][] lane = new Object[3][15];


        Random rand = new Random();
        for(int i=0; i< 3; i++){
            lane[rand.nextInt(3)][rand.nextInt(15)] = "STOP!";
        }

        Car one = new Car(lane, "Audi", 0, 1);
        Car two = new Car(lane, "BMW", 1, 2);
        Car three = new Car(lane, "Volkswagen", 2, 5);

        one.start();
        two.start();
        three.start();
    }
}
