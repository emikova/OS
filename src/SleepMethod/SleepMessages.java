package SleepMethod;

public class SleepMessages {
    public static void main(String[] args) throws InterruptedException{
            String ImportantInfo[] = {
                    "Do you know the muffin man?",
                    "The muffin man?",
                    "The muffin man!",

            };

            for(int i = 0; i<ImportantInfo.length; i++){

                Thread.sleep(4000);


                System.out.println(ImportantInfo[i]);
            }
    }
}
