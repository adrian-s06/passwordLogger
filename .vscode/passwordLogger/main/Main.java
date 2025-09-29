package passwordLogger.main;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList; 

public class Main{
    
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main (String[] args) throws IOException { 
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();
        FileOutputStream fileStream = new FileOutputStream("password.csv");
        PrintWriter outFS = new PrintWriter(fileStream);
        
        /*
         * password must include the following:
         * Capital
         * lower case
         * number
         * symbol
        */
        String finPassword = null;
        char genPassword = 'Y';

        while (genPassword == 'Y') {
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

            finPassword = String.valueOf(password);
            System.out.println("The password that is generated is: " + finPassword);
            
            //Option to regenerate password of desired
                System.out.println("------------------------------------------------");
                System.out.println("Do you wish to re generate the password? Type Y or N to confirm:"); 
                genPassword = scnr.next().charAt(0);
            
                while (genPassword != 'Y' && genPassword != 'N') {
                    System.out.println("Please input either Y or N");
                    genPassword = scnr.next().charAt(0);
                }
        }
        
        
        // prepping website instance
            ArrayList<Website> list = new ArrayList<>();
            Website website = new Website();
            list.add(website);
        char createNewWebsite = 'Y';
        while (createNewWebsite == 'Y') {        
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
            
            System.out.println("Do you want to redo your Url or Username: Type Y or N ");
            createNewWebsite = scnr.next().charAt(0);

            while (createNewWebsite != 'Y' && createNewWebsite != 'N') {
                System.out.println("Please input either Y or N");
                createNewWebsite = scnr.next().charAt(0);
            }
        }
        
        
        // adding to csv file
            outFS.printf("\"url\",\"username\",\"password\",\"httpRealm\",\"formActionOrigin\",\"guid\",\"timeCreated\",\"timeLastUsed\",\"timePasswordChanged\"\n");
            for (int i = 0; i < list.size(); i++) {
                list.get(i).saveInfo(outFS);
            }
        
        scnr.close();
        outFS.close();
    }
}