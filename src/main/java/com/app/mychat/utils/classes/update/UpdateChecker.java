package com.app.mychat.utils.classes.update;

import com.app.mychat.utils.classes.backend.UserDetails;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateChecker {

    private GitHub gitHub;
    private HttpsURLConnection httpsURLConnection;

    public UpdateChecker(GitHub gitHub){
        this.gitHub = gitHub;
    }

    public boolean createConnection(){
        try{
            URL url = new URL(gitHub.getLink());
            httpsURLConnection = (HttpsURLConnection) url.openConnection();
            return httpsURLConnection.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String convertToStringVersion(long versionId){
        return String.format("v%d.%d.%d", versionId%1000, versionId%100, versionId%10);
    }

    public boolean isCurrentVersionOlder(){
        return UserDetails.latestVersionID > UserDetails.clientVersionID;
    }

    private void downloadUpdate(){
        //Download the file here using the above functions
        // Order -
        // 1. Check if the current version is older or not.
        // 2. If yes, Create a connection to github link -> convert the long version id to string using the convertToStringVersion() function
        // 3. Parse the version id to the github object then begin the download
    }

}
