package by.ip.ftp;

import java.io.IOException;

/**
 * Created by ivanpryshchepau on 6/16/16.
 */
public class Command {

    public static void function(String[] command) {
        switch (command[0]) {
            case "connect": {
                try {
                    Connect.connection(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printHelp();
                }
                break;
            }
            case "disconnect": {
                try {
                    if (Connect.client.isConnected()) {
                        Connect.client.logout();
                        Connect.client.disconnect();
                        System.out.println("Disconnected");
                    } else {
                        System.out.println("Connection is not open");
                    }
                } catch (IOException e) {
                    printHelp();
                }
                break;
            }
            case "ls": {
                if (Connect.client.isConnected()) {
                    Connect.getList();
                } else {
                    System.out.println("Please connect to server");
                    System.err.println("Write 'help' to show commands");
                }

                break;
            }
            case "cd": {
                try {
                    Connect.changeDirectory(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    printHelp();
                }
                break;
            }
            case "dl": {
                try {
                    Connect.downloadFile(command[1], command[2]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Please use command correctly. (dl [filename] [path to save/name])");
                    System.err.println("Write 'help' to show commands");
                }
                break;
            }
            case "status": {
                try {
                    System.out.println(Connect.client.getStatus());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "exit": {
                try {
                    if (Connect.client.isConnected()) {
                        Connect.client.logout();
                        Connect.client.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.exit(0);
                break;
            }
            case "help":
            default: {
                printHelp();
                break;
            }
        }
    }

    private static void printHelp() {
        System.out.println("Commands:");
        System.out.println("Use 'connect' to connect to Server (connect [host])");
        System.out.println("Use 'disconnect' to disconnect from Server");
        System.out.println("Use 'ls' to connect to see directories and files in current directory");
        System.out.println("Use 'cd' to connect to change current directory (cd [dir]) /absoutePath, 'relativePath'");
        System.out.println("Use 'dl' to connect to download file (dl [file] [path to save])");
        System.out.println("Use 'status' to show server status");
        System.out.println("To close use 'exit'");
    }

}
