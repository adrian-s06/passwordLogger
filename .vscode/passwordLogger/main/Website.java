package passwordLogger.main;
import java.io.PrintWriter;
public class Website {
      
    private String webUrl, webUsername, webPassword;
   
    //constructor
        public Website() {
            webUrl = null;
            webUsername = null;
            webPassword = null;
        }
   
    // set methods
        public void setUrl(String url) {
            webUrl = url;
        }
       
        public void setUsername(String userName) {
            webUsername = userName;
        }
       
        public void setPassword(String password) {
            webPassword = password;
        }
   
    //get methods
        public String getUrl() {
            return webUrl;
        }
       
        public String getUsername() {
            return webUsername;
        }  
       
        public String getPassword() {
            return webPassword;
        }
   
    public void viewInfo() {
        System.out.println("-----------------------------------");
        System.out.println("Here are the following details:");
        System.out.printf("Url: %s\nUsername: %s\nPassword: %s ", webUrl, webUsername, webPassword);
       
    }
    
    public void saveInfo(PrintWriter output) {
        output.println("");
    }

}