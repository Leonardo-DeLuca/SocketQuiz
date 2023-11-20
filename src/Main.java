import Client.SocketClient;
import Server.SocketServer;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        SocketServer ss = new SocketServer();
        SocketClient sc = new SocketClient();

        (new Thread(() -> {
            try {
                ss.exec();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })).start();

        (new Thread(() -> {
            try {
                sc.exec();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })).start();
    }
}
