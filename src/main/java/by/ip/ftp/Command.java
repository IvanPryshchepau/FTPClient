package by.ip.ftp;

import java.io.IOException;

/**
 * Created by ivanpryshchepau on 6/16/16.
 */
public class Command {

    public static void function(String[] command) {
        switch (command[0]) {
            case "connect": {
                Connect.connection(command[1]);
                break;
            }
            case "disconnect": {
                try {
                    Connect.client.logout();
                    Connect.client.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "ls": {
                Connect.getList();
                break;
            }
            case "cd": {
                Connect.changeDirectory(command[1]);
                break;
            }
            case "dl": {
                try {
                    Connect.downloadFile(command[1], command[2]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.err.println("Please use command correctly. (dl [filename] [path to save])");
                }
                break;
            }
            case "exit": {
                System.exit(0);
                break;
            }
            default: {
                System.out.println("Incorrect command");
                System.out.println("Use 'connect' to connect to Server (connect [host])");
                System.out.println("Use 'disconnect' to disconnect from Server");
                System.out.println("Use 'ls' to connect to see directories and files in current directory");
                System.out.println("Use 'cd' to connect to change current directory (cd [dir]) /absoutePath, 'relativePath'");
                System.out.println("Use 'dl' to connect to download file (dl [file] [path to save])");
                System.out.println("To close use 'exit'");
                break;
            }
        }
    }

}
