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
        // initialising objects and variables
            Scanner scnr = new Scanner(System.in);
            Random rand = new Random();
            FileOutputStream fileStream = new FileOutputStream("password.csv");
            PrintWriter outFS = new PrintWriter(fileStream);
            ArrayList<Website> list = new ArrayList<>();
        
        
        // prepping boolean flags
           
            char editWebsiteData = 'Y';
            String finPassword = null;
            char genPassword = 'Y';
            String createNewWebsite = "new";


        while (createNewWebsite.equalsIgnoreCase("new")) {
            Website website = new Website();
            editWebsiteData = 'Y';
            finPassword = null;
            genPassword = 'Y';
            createNewWebsite = "new";

            //Complete password Generator
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
            
            //allows user to put url and username of website
                while (editWebsiteData == 'Y') {        
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
                
                System.out.println("------------------------------------------------------");
                System.out.println("Do you want to redo your Url or Username: Type Y or N");
                editWebsiteData = scnr.next().charAt(0);

                while (editWebsiteData != 'Y' && editWebsiteData != 'N') {
                    System.out.println("Please input either Y or N");
                    editWebsiteData = scnr.next().charAt(0);
                }
                
            }
            list.add(website);

            // gives option to create another website
                System.out.println("Do you wish to continue or record another new website: Type continue or new");
                createNewWebsite = scnr.next();
                while (!createNewWebsite.equalsIgnoreCase("continue") && !createNewWebsite.equalsIgnoreCase("new")) {
                    System.out.println("Please enter either Continue or New");
                    createNewWebsite = scnr.next(); 
                }
        }
        // adding to csv file by printing whole ArrayList
            outFS.printf("\"url\",\"username\",\"password\",\"httpRealm\",\"formActionOrigin\",\"guid\",\"timeCreated\",\"timeLastUsed\",\"timePasswordChanged\"\n");
            for (int i = 0; i < list.size(); i++) {
                list.get(i).saveInfo(outFS);
            }
        
        scnr.close();
        outFS.close();
    }
}