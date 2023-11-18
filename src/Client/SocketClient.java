package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;

public class SocketClient {

    public void exec() throws IOException{

        int contaPerguntas = 0;
        Boolean gameOver = false;

        while(!gameOver && contaPerguntas < 5) {
            Socket clientSocket = inicializandoSocket();
            DataInputStream inbound = geraStreamInput(clientSocket);
            DataOutputStream outbound = geraStreamOutput(clientSocket);

            String perguntaTitulo = inbound.readUTF();

            System.out.println(perguntaTitulo);
            System.out.println(inbound.readFloat());
            Scanner sc = new Scanner(System.in);
            sc.useLocale(Locale.US);

            float respostaCliente = sc.nextFloat();

            outbound.writeFloat(respostaCliente);

            contaPerguntas++;
            gameOver = inbound.readBoolean();

            inbound.close();
            outbound.close();
            clientSocket.close();
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
