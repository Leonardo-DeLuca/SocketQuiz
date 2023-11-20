package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {
    JPanel panel;
    JTextField perguntaTextField;
    JTextField respostaTextField;
    private Float resposta;

    public Interface() {
        super("Quiz Matemático");

        panel = new JPanel();
        GridLayout layout = new GridLayout(3, 1);
        layout.setVgap(20);
        panel.setLayout(layout);

        perguntaTextField = new JTextField();
        perguntaTextField.setEditable(false);
        perguntaTextField.setHorizontalAlignment(JTextField.CENTER);
        perguntaTextField.setFont(new Font("SansSerif", Font.BOLD, 22));

        respostaTextField = new JTextField();
        respostaTextField.setHorizontalAlignment(JTextField.CENTER);

        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resposta = Float.valueOf(respostaTextField.getText());
            }
        });

        panel.add(perguntaTextField);
        panel.add(respostaTextField);
        panel.add(enviarButton);

        add(panel);

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void setPergunta(String pergunta) {
        perguntaTextField.setText(pergunta);
    }

    public Float getResposta() {
        return resposta;
    }

    public void setResposta(Float resposta) {
        this.resposta = resposta;
    }

    public void exibirMensagem(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem);
    }


}
