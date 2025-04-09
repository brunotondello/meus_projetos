package projetos.meusprojetos.iniciante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InversorDePalavras extends JFrame {

    private JTextField inputField;
    private JButton inverterButton;
    private JLabel resultadoLabel;

    public InversorDePalavras() {
        setTitle("Inversor de Palavras");
        setSize(400, 150);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        inputField = new JTextField(15);
        inverterButton = new JButton("Inverter");
        resultadoLabel = new JLabel("Resultado aqui");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Digite uma palavra:"));
        panel.add(inputField);
        panel.add(inverterButton);
        panel.add(resultadoLabel);

        inverterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palavra = inputField.getText();
                String invertida = inverterPalavra(palavra);
                resultadoLabel.setText("Invertida: " + invertida);
            }
        });

        add(panel);
        setVisible(true);
    }

    private String inverterPalavra(String palavra) {
        System.out.println("Palavra recebida: " + palavra);
        return new StringBuilder(palavra).reverse().toString();
    }
}
