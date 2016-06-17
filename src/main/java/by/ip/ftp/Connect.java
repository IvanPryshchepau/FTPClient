package by.ip.ftp;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;

/**
 * Created by ivanpryshchepau on 6/16/16.
 */
public class Connect {

    static FTPClient client = new FTPClient();

    static String[] fileList;


    public static void connection(String host) {
        try {
            client.connect(host);
            System.out.println("Connected to " + host);
            client.enterLocalPassiveMode();
            client.login("anonymous", "");
            if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
                client.disconnect();
                System.err.println("FTP server refused connection.");
                System.exit(1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not connect to server.");
            System.exit(1);
        }

    }


    public static String[] getList() {

        try {
            fileList = client.listNames();
            for (FTPFile f : client.listFiles()) {
                if(!f.isSymbolicLink()) {
                    System.out.println(f.getName() + (f.isDirectory() ? "/" : ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileList;
    }

}
