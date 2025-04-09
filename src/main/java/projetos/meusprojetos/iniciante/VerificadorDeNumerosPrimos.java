package projetos.meusprojetos.iniciante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerificadorDeNumerosPrimos extends JFrame {

    private JTextField inputField;
    private JButton verificarButton;
    private JLabel resultadoLabel;

    public VerificadorDeNumerosPrimos() {

        setTitle("Verificador de Número Primo");
        setSize(400, 150);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        inputField = new JTextField(10);
        verificarButton = new JButton("Verificar");
        resultadoLabel = new JLabel("Resultado aparecerá aqui");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Digite um número:"));
        panel.add(inputField);
        panel.add(verificarButton);
        panel.add(resultadoLabel);

        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int num = Integer.parseInt(inputField.getText());
                    String resultado = verificarNumeroPrimo(num);
                    resultadoLabel.setText(resultado);
                } catch (NumberFormatException ex) {
                    resultadoLabel.setText("Digite um número inteiro válido!");
                }
            }
        });

        add(panel);
        setVisible(true);

    }

    private String verificarNumeroPrimo(int num) {
        if (num < 2) {
            return "O número '" + num + "' não é primo. O menor número primo é 2.";
        } else {
            boolean ehPrimo = true;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    ehPrimo = false;
                    break;
                }
            }
            if (ehPrimo) {
                return "O número '" + num + "' é primo.";
            } else {
                return "O número '" + num + "' não é primo.";
            }
        }
    }

/*    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VerificadorDeNumerosPrimos().setVisible(true);
        });
    }*/
}
