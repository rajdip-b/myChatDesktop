package com.app.mychat.utils.classes.update;

public class GitHub {

    private String githubReleaseLink;

    public GitHub(String versionID){
        githubReleaseLink = "https://github.com/rajdip-b/myChatDesktop/releases/tag/" + versionID;
    }

    public String getLink(){
        return githubReleaseLink;
    }

}
