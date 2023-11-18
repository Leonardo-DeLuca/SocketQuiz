package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

public class SocketServer {

    private static final DecimalFormat df = new DecimalFormat("#.##");

    public void exec() throws IOException{
        ServerSocket server = inicializandoSocket();
        DataInputStream inbound;
        DataOutputStream outbound;
        Pergunta pergunta;
        Boolean gameOver = false;
        int contaPerguntas = 0;

        while(!gameOver && contaPerguntas < 5){
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            inbound = geraStreamInput(socket);
            outbound = geraStreamOutput(socket);
            pergunta = new Pergunta();

            String perguntaTitulo = pergunta.getTitulo();
            Float respostaCorreta = pergunta.getResposta();
            Float respostaCliente;

            outbound.writeUTF(perguntaTitulo);
            outbound.writeFloat(respostaCorreta);
            respostaCliente = inbound.readFloat();

            String strRespostaCorretaFormatada;
            df.setRoundingMode(java.math.RoundingMode.DOWN);
            strRespostaCorretaFormatada = df.format(respostaCorreta);

            strRespostaCorretaFormatada = strRespostaCorretaFormatada.replace(',', '.');
            respostaCorreta = Float.parseFloat(strRespostaCorretaFormatada);


            if(respostaCliente.equals(respostaCorreta)){
                System.out.println("Resposta Correta!");
            }else {
                System.out.println("Resposta Errada!");
                gameOver = true;
            }

            contaPerguntas++;
            outbound.writeBoolean(gameOver);

            inbound.close();
            outbound.close();
            socket.close();
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
