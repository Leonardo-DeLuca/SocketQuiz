package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {

    public static void main(String[] args) throws IOException, InterruptedException {
            Socket clientSocket = inicializandoSocket();
            DataInputStream inbound = geraStreamInput(clientSocket);
            DataOutputStream outbound = geraStreamOutput(clientSocket);

            String perguntaTitulo = inbound.readUTF();

            System.out.println(perguntaTitulo);
            System.out.println(inbound.readFloat());
            Scanner sc = new Scanner(System.in);

            float respostaCliente = sc.nextFloat();

            outbound.writeFloat(respostaCliente);

            inbound.close();
            outbound.close();
            clientSocket.close();
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
