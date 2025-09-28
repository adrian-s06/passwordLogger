package passwordLogger.main;
import java.io.PrintWriter;
import java.util.UUID;
public class Website {
      
    private String webUrl, webUsername, webPassword, guid;
    private long currTime;
   
    //constructor
        public Website() {
            webUrl = null;
            webUsername = null;
            webPassword = null;
            
            guid = "{" + UUID.randomUUID().toString() + "}";
            currTime = System.currentTimeMillis();

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
        System.out.printf("Url: %s\nUsername: %s\nPassword: %s\n ", webUrl, webUsername, webPassword);
       
    }
    
    public void saveInfo(PrintWriter outFS) {
        //format: "url","username","password","httpRealm","formActionOrigin","guid","timeCreated","timeLastUsed","timePasswordChanged"
        outFS.printf("\"url\",\"username\",\"password\",\"httpRealm\",\"formActionOrigin\",\"guid\",\"timeCreated\",\"timeLastUsed\",\"timePasswordChanged\"\n");
        outFS.printf("\"%s\",\"%s\",\"%s\",,\"%s\",\"%s\",\"%d\",\"%d\",\"%d\"\n", webUrl , webUsername , webPassword , webUrl, guid , currTime , currTime, currTime );
    }

}