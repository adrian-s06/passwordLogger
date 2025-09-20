package passwordLogger;
import java.util.Random;
import java.util.Scanner;
public class Main{
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main (String[] args) { 
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();
        
        /*
         * password must include the following:
         * Capital
         * lower case
         * number
         * symbol
        */

        //getting password length
            System.out.println("Please enter the number of characters for your password (must be at least 4 characters):");
        
            int passLength = scnr.nextInt();
            while (passLength < 4) {
                System.out.println("------------------------------------------------------");
                System.out.println("Invalid number of characters, please enter a number over 4");
                passLength = scnr.nextInt();
            }

        //password generator
            char[] password = new char[passLength];
            
            password[0] = (char) (rand.nextInt(26) + 63); // capital
            password[1] = (char) (rand.nextInt(26) + 95); // lower case
            password[2] = (char) (rand.nextInt(9) + 47);// number
            password[3] = (char) (rand.nextInt(15) + 33); // symbol

            for (int i = 4; i < passLength; i++) {
                password[i] = (char) (rand.nextInt(94) + 32);
            }

        //password randomiser
        
        
        // prepping website instance
            String url = scnr.nextLine();
            String userName = scnr.nextLine();
            String finPassword = "Test";

            Website website = new Website();
        
            website.setUrl(url);
            website.setUsername(userName);
            website.setPassword(finPassword);
        
            website.viewInfo();
        
        scnr.close();
    }
}