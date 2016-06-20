package by.ip.ftp;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ivanpryshchepau on 6/16/16.
 */
public class Connect {

    static final FTPClient client = new FTPClient();

    public static void connection(String host) {
        try {
            client.connect(host);
            System.out.println("Connected to " + host);
            client.enterLocalPassiveMode();
            client.login("anonymous", "");
            if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
                System.err.println("FTP server refused connection.");
            }
        } catch (IOException e) {
            System.err.println("Cannot connect to server.");
        }

    }

    public static void getList() {

        try {
            for (FTPFile f : client.listFiles()) {
                if(!f.isSymbolicLink()) {
                    System.out.println(f.getName() + (f.isDirectory() ? "/" : ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void changeDirectory(String dir) {
        try {
            client.changeWorkingDirectory(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void downloadFile(String filename, String pathToSave) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(pathToSave);
            client.retrieveFile(filename, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }

}
