package passwordLogger.main;
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
            for (int i = 0; i < passLength; i++) {
                char tempVal = password[i];
                int randIndex = rand.nextInt(passLength);

                password[i] = password[randIndex];
                password[randIndex] = tempVal;
                
            }

        String finPassword = String.valueOf(password);
        System.out.println("The password that is generated is: " + finPassword);

        // TODO: Make an option to regenerate the password by nexting whole password creation process in a while loop
        //System.out.println("Do you wish to continue? Type Y or N to confirm:"); 

        // prepping website instance
            Website website = new Website();
            
            System.out.println("------------------------------------------------------------");
            System.out.println("Please enter the URL of the password menu of select website: ");
            
            String url = scnr.next(); 
            website.setUrl(url);
            
            System.out.println("---------------------------------------------------------");
            System.out.println("Please enter your username/email for your select website:");
            
            String userName = scnr.next();
            website.setUsername(userName);

            website.setPassword(finPassword);
        
            website.viewInfo();
        
        scnr.close();
    }
}