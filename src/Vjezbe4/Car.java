package Vjezbe4;

public class Car extends Thread{
    String type;
    int id;
    Object[][] lane;
    int row;
    int the_rest;
    static boolean the_end = false;


    public Car(Object[][] lane, String type, int id, int row){
        this.lane = lane;
        this.type = type;
        this.id = id;
        this.row = row;
        this.the_rest = lane[0].length-1;
    }

    @Override
    public void run(){
        for(int i = 0; i < 15; i++){
            if(the_end){
                System.out.println("The one who lost is: " +  this);
                break;
            }else {
                if (lane[row][i] != null && lane[row][i].equals("STOP!")){
                    try{
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("Stop -> " + this);
                }else{
                    try {
                        Thread.sleep(200);

                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(this);
                }
            }
            if(i == 14){
                the_end = true;
                System.out.println("The winner is: " + this);
                interrupt();
            }
            the_rest--;
        }

    }

    @Override
    public String toString(){
        return "Car[type: " + type + ", id: " + id + "till finish: " + the_rest + "]";
    }
}
