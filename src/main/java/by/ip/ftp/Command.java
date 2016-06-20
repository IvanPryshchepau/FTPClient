package by.ip.ftp;

import java.io.IOException;

/**
 * Created by ivanpryshchepau on 6/16/16.
 */
public class Command {

    private static String swCommands;
    private static String host;

    public static void function(String[] commands) {

        swCommands = commands[0].trim().toLowerCase();

        switch (swCommands) {
            case "connect": {
                try {
                    Connect.connection(commands[1].trim());
                } catch (ArrayIndexOutOfBoundsException e) {
                    printHelp();
                } catch (IOException e) {
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
                        System.err.println("Write 'help' to show commands");
                    }
                } catch (IOException e) {
                    printHelp();
                }
                break;
            }
            case "ls": {
                try {
                    if (Connect.client.isConnected()) {
                        Connect.getList();
                    } else {
                        System.out.println("Please connect to server");
                        System.err.println("Write 'help' to show commands");
                    }
                } catch (IOException e) {
                    printHelp();
                }

                break;
            }
            case "cd": {
                try {
                    if (Connect.client.isConnected()) {
                        Connect.changeDirectory(commands[1]);
                    } else {
                        System.out.println("Please connect to server");
                        System.err.println("Write 'help' to show commands");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    printHelp();
                } catch (IOException e) {
                    printHelp();
                }
                break;
            }
            case "dl": {
                try {
                    if (Connect.client.isConnected()) {
                        Connect.downloadFile(commands[1].trim(), commands[2].trim());
                    } else {
                        System.out.println("Please connect to server");
                        System.err.println("Write 'help' to show commands");
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Please use commands correctly. (dl [filename] [path to save/name])");
                    System.err.println("Write 'help' to show commands");
                } catch (IOException e) {
                    System.err.println("Please use commands correctly. (dl [filename] [path to save/name])");
                    System.err.println("Write 'help' to show commands");
                }
                break;
            }
            case "status": {
                try {
                    if (Connect.client.isConnected()) {
                        System.out.println(Connect.client.getStatus());
                    } else {
                        System.out.println("Please connect to server");
                        System.err.println("Write 'help' to show commands");
                    }
                } catch (IOException e) {
                    printHelp();
                }
                break;
            }
            case "curr": {
                try {
                    if (Connect.client.isConnected()) {
                        System.out.println("Current directory: " + Connect.client.printWorkingDirectory());
                    } else {
                        System.out.println("Please connect to server");
                        System.err.println("Write 'help' to show commands");
                    }
                } catch (IOException e) {
                    printHelp();
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
                    printHelp();
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
        System.out.println("Use 'curr' to connect to path of current directory");
        System.out.println("Use 'cd' to connect to change current directory (cd [dir]) /absoutePath or 'relativePath'");
        System.out.println("Use 'dl' to connect to download file (dl [file] [path to save/filename])");
        System.out.println("Use 'status' to show server status");
        System.out.println("To close use 'exit'");
    }

}
