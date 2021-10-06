package com.app.mychat.utils.classes.update;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateChecker {

    private GitHub gitHub;

    public UpdateChecker(GitHub gitHub){
        this.gitHub = gitHub;
    }

    public void checkForVersion(){
        try{
            URL url = new URL(gitHub.getLink());
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            if (httpsURLConnection.getResponseCode() == 200)
                System.out.println("Version available");
            else
                System.out.println("Failure!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
