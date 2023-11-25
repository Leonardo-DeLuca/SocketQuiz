package Client;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient{

    public void exec() throws IOException{

        int maxPerguntas = 5;
        int contaPerguntas = 0;
        Boolean gameOver = false;

        Interface intfc = new Interface();

        while(!gameOver && contaPerguntas < maxPerguntas) {
            Socket clientSocket = inicializandoSocket();
            DataInputStream inbound = geraStreamInput(clientSocket);
            DataOutputStream outbound = geraStreamOutput(clientSocket);

            String perguntaTitulo = inbound.readUTF();

            intfc.setPergunta(perguntaTitulo);

            while (intfc.getResposta() == null) {
                try {
                    Thread.sleep(100); // Aguarda um curto período antes de verificar novamente
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            float respostaCliente = intfc.getResposta();

            outbound.writeFloat(respostaCliente);

            gameOver = inbound.readBoolean();

            if(gameOver){
                intfc.exibirMensagem("Resposta incorreta finalizando o jogo com " + contaPerguntas + "/" + maxPerguntas + " perguntas corretas!");
                intfc.dispose();
            }
            else{
                intfc.exibirMensagem("Resposta Correta!");
                intfc.respostaTextField.setText("");
            }

            inbound.close();
            outbound.close();
            clientSocket.close();

            intfc.setResposta(null);

            contaPerguntas++;

            if(!gameOver && contaPerguntas == 5){
                intfc.exibirMensagem("Fim de jogo! Você acertou todas as perguntas!");
                intfc.dispose();
            }

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
