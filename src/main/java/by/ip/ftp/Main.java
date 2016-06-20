package by.ip.ftp;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ivanpryshchepau on 6/16/16.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("FTPClient");
        System.out.println("Write command and press Enter. To show commands write 'help'");

        while (true) {
            Scanner scannerMenu = new Scanner(System.in);
            String[] menu = scannerMenu.nextLine().trim().split(" ");
            Command.function(menu);
            //Connect.showServerReply(Connect.client);
        }

    }

}
