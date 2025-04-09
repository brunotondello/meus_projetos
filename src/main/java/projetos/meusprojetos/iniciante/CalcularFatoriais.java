package projetos.meusprojetos.iniciante;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

public class CalcularFatoriais extends JFrame {

    private JTextField inputField;
    private JButton calcularButton;
    private JLabel resultadoLabel;

    public CalcularFatoriais() {
        setTitle("Calculadora de Fatorial");
        setSize(400, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza a janela

        inputField = new JTextField(10);
        calcularButton = new JButton("Calcular");
        resultadoLabel = new JLabel("Resultado aqui");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Digite um número:"));
        panel.add(inputField);
        panel.add(calcularButton);
        panel.add(resultadoLabel);

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int n = Integer.parseInt(inputField.getText());
                    BigInteger resultado = calcularFatorial(n);
                    resultadoLabel.setText("Fatorial: " + resultado.toString());
                } catch (NumberFormatException ex) {
                    resultadoLabel.setText("Digite um número válido!");
                }
            }
        });

        add(panel);

        setVisible(true);
    }

    private BigInteger calcularFatorial(int n) {
        BigInteger fatorial = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            fatorial = fatorial.multiply(BigInteger.valueOf(i));
        }
        return fatorial;
    }

}
