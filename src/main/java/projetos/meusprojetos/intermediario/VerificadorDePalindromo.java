package projetos.meusprojetos.intermediario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerificadorDePalindromo extends JFrame {

    private JTextField inputField;
    private JButton verificarButton;
    private JLabel resultadoLabel;

    public VerificadorDePalindromo() {
        setTitle("Verificador de Palíndromo");
        setSize(400, 150);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        inputField = new JTextField(15);
        verificarButton = new JButton("Verificar");
        resultadoLabel = new JLabel("Resultado aqui");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Digite uma palavra ou frase:"));
        panel.add(inputField);
        panel.add(verificarButton);
        panel.add(resultadoLabel);

        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String palavra = inputField.getText();
                boolean ehPalindromo = verificaPalindromo(palavra);
                resultadoLabel.setText(ehPalindromo ? "É palíndromo!" : "Não é palíndromo.");
            }
        });

        add(panel);
        setVisible(true);
    }

    private boolean verificaPalindromo(String palavra) {
        try {
            palavra = palavra.replaceAll("\\s", "").toLowerCase();

            int tamanho = palavra.length();

            for (int i = 0; i < tamanho / 2; i++) {
                if (palavra.charAt(i) != palavra.charAt(tamanho - i - 1)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return false;
        }
    }
}
