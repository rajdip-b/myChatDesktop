package com.app.mychat.utils.classes.update;

import java.io.File;
import java.net.URL;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;

public class Update extends UpdateChecker {

    public Update(GitHub gitHub) {
        super(gitHub);
    }

    public static void main(String[] args) {
        new UpdateChecker(new GitHub("v2.0.0")).checkForVersion();
        Update update=new Update(GitHub github);
        update.isLatest();
        String s_url="https://github.com/rajdip-b/myChatDesktop/releases/tag/";
        URL url=new URL(s_url);
        File file =new File("C:/Temp/file.zip");
        if (update.isLatest() == false)
            update.copyURLToFile(url, file);
    }

    public boolean isLatest()
    {
        if (clientVersionID == latestVersionID) {
            System.out.println("Already latest version is installed!!!");
            return true;
        }
        else
            if (latestVersionID > clientVersionID)
            {
                System.out.println("Updating to latest version");;
                return false;
            }
    }


    public static void copyURLToFile(URL url, File file)
    {
        try {
            InputStream input = url.openStream();
            if (file.exists()) {
                if (file.isDirectory())
                    throw new IOException("File '" + file + "' is a directory");

                if (!file.canWrite())
                    throw new IOException("File '" + file + "' cannot be written");
            } else {
                File parent = file.getParentFile();
                if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
                    throw new IOException("File '" + file + "' could not be created");
                }
            }

            FileOutputStream output = new FileOutputStream(file);

            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }

            input.close();
            output.close();

            System.out.println("File '" + file + "' downloaded successfully!");
        }
        catch(IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
