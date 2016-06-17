package by.ip.ftp;

/**
 * Created by ivanpryshchepau on 6/16/16.
 */
public class Main {

    public static void main(String[] args) {
        Connect.connection(args[0]);

        Connect.logIn("anonymous", "");

        for (String file:Connect.getList()) {
            System.out.println(file);
        }
    }

}
