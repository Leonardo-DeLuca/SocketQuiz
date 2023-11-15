package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        while(true) {
            Socket clientSocket = inicializandoSocket();
            DataInputStream inbound = geraStreamInput(clientSocket);
            DataOutputStream outbound = geraStreamOutput(clientSocket);

            System.out.println("Escreva um int: ");
            Scanner sc = new Scanner(System.in);

            int message = sc.nextInt();

            outbound.writeInt(message);

            inbound.close();
            outbound.close();
            clientSocket.close();

            if (message == 0) break;

        }
    }

    public static Socket inicializandoSocket() throws IOException {
        InetAddress host = InetAddress.getLocalHost();

        Socket clientSocket = new Socket(host, 9888);

        return clientSocket;
    }

    public static DataInputStream geraStreamInput(Socket clientSocket) throws IOException {
        DataInputStream inbound = new DataInputStream
                ( clientSocket.getInputStream( ) );

        return inbound;

    }

    public static DataOutputStream geraStreamOutput(Socket clientSocket) throws IOException {
        DataOutputStream outbound = new DataOutputStream
                ( clientSocket.getOutputStream( ) );

        return outbound;
    }



}
