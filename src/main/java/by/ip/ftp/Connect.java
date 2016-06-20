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

    public static void connection(String host) throws IOException {
        client.connect(host);
        System.out.println("Connected to " + host);
        client.enterLocalPassiveMode();
        client.login("anonymous", "");
        if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
            System.err.println("FTP server refused connection.");
        }
    }

    public static void getList() throws IOException {
        for (FTPFile file : client.listFiles()) {
            if (!file.isSymbolicLink()) {
                System.out.println(file.getName() + (file.isDirectory() ? "/" : ""));
            }
        }
    }

    public static void changeDirectory(String dir) throws IOException {
        client.changeWorkingDirectory(dir);
    }

    public static void downloadFile(String filename, String pathToSave) throws IOException {
        FileOutputStream fos = null;
        fos = new FileOutputStream(pathToSave);
        client.retrieveFile(filename, fos);
        if (fos != null) {
            fos.close();
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
