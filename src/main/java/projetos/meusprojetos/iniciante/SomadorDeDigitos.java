package projetos.meusprojetos.iniciante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SomadorDeDigitos extends JFrame {

    private JTextField inputField;
    private JButton somarButton;
    private JLabel resultadoLabel;

    public SomadorDeDigitos() {
        setTitle("Soma dos Dígitos");
        setSize(400, 150);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        inputField = new JTextField(10);
        somarButton = new JButton("Somar Dígitos");
        resultadoLabel = new JLabel("Resultado aqui");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Digite um número:"));
        panel.add(inputField);
        panel.add(somarButton);
        panel.add(resultadoLabel);

        somarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(inputField.getText());
                    int soma = somarDigitos(numero);
                    resultadoLabel.setText("Soma dos dígitos: " + soma);
                } catch (NumberFormatException ex) {
                    resultadoLabel.setText("Digite um número válido!");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    private int somarDigitos(int numero) {
        numero = Math.abs(numero);
        int soma = 0;
        while (numero > 0) {
            soma += numero % 10;
            numero /= 10;
        }
        return soma;
    }
}
