package Interruptions;

public class InterruptingSleepMessages {
    public static void main(String[] args)  {
        String ImportantInfo[] = {
                "Did anybody tell you you look like Beyonce?",
                "Nah, they usually tell me I look like Shallysa",
                "Who tf is that?",
                "Me mf"
        };

        for(int i = 0; i < ImportantInfo.length; i++){

            try {
                Thread.sleep(4000);
            }catch (InterruptedException e){

                return;
            }
            System.out.println(ImportantInfo[i]);
        }
    }
}
