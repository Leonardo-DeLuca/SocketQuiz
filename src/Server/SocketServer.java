package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = inicializandoSocket();
        DataInputStream inbound;
        DataOutputStream outbound;
        while(true){
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            inbound = geraStreamInput(socket);
            outbound = geraStreamOutput(socket);

            int k = inbound.readInt();

            System.out.println(k);

            inbound.close();
            outbound.close();
            socket.close();

            if(k == 0) break;
        }

        System.out.println("Shutting down Socket server!!");
        server.close();


    }

    public static ServerSocket inicializandoSocket() throws IOException {

        ServerSocket server = new ServerSocket(9888);

        return server;
    }

    public static DataInputStream geraStreamInput(Socket server) throws IOException {
        DataInputStream inbound = new DataInputStream
                ( server.getInputStream( ) );

        return inbound;

    }

    public static DataOutputStream geraStreamOutput(Socket server) throws IOException {
        DataOutputStream outbound = new DataOutputStream
                ( server.getOutputStream( ) );

        return outbound;
    }



}
