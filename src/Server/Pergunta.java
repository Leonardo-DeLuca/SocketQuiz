package Server;

import java.util.Random;

public class Pergunta{

    private String titulo;
    private float resposta;
    private float[] numeros;
    private String[] operacoes;

    public Pergunta() {
        setaValores();
        definePergunta();
    }

    private void setaValores(){
        numeros = new float[100];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = i;
        }
        operacoes = new String[]{"+", "-", "*", "/"};
    }

    private void definePergunta(){
        float valor1, valor2;
        String operacao;

        Random indiceRandom = new Random();

        valor1 = numeros[indiceRandom.nextInt(numeros.length)];
        valor2 = numeros[indiceRandom.nextInt(numeros.length)];

        operacao = operacoes[indiceRandom.nextInt(operacoes.length)];

        titulo = valor1 + " " + operacao + " " + valor2;

        if(operacao.equals("+")){
            resposta = valor1+valor2;
        } else if(operacao.equals("-")){
            resposta = valor1-valor2;
        }else if(operacao.equals("*")){
            resposta = valor1*valor2;
        } else if(operacao.equals("/")){
            resposta = valor1/valor2;
        }

    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getResposta() {
        return resposta;
    }

    public void setResposta(float resposta) {
        this.resposta = resposta;
    }
}
