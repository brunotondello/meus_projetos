package projetos.meusprojetos.intermediario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversorDeBase extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConversorDeBase::new);
    }

    private JTextField inputField;
    private JButton decimalParaBinarioBtn;
    private JButton binarioParaDecimalBtn;

    public ConversorDeBase() {
        setTitle("Conversor de Base");
        setSize(400, 200);
        setResizable(false); // Impede redimensionamento da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLocationRelativeTo(null); // Centraliza a janela

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel label = new JLabel("Digite o número:", JLabel.CENTER);
        panel.add(label);

        inputField = new JTextField();
        panel.add(inputField);

        // Botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        decimalParaBinarioBtn = new JButton("Decimal → Binário");
        binarioParaDecimalBtn = new JButton("Binário → Decimal");

        buttonPanel.add(decimalParaBinarioBtn);
        buttonPanel.add(binarioParaDecimalBtn);

        panel.add(buttonPanel);

        // Ações dos botões
        decimalParaBinarioBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int decimal = Integer.parseInt(inputField.getText());
                    String binario = decimalParaBinario(decimal);
                    JOptionPane.showMessageDialog(null, "Binário: " + binario);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número decimal válido.");
                }
            }
        });

        binarioParaDecimalBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String binario = inputField.getText();
                if (binario.matches("[01]+")) {
                    int decimal = binarioParaDecimal(binario);
                    JOptionPane.showMessageDialog(null, "Decimal: " + decimal);
                } else {
                    JOptionPane.showMessageDialog(null, "Digite apenas 0 e 1 (número binário válido).");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    // Métodos de conversão
    private String decimalParaBinario(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    private int binarioParaDecimal(String binario) {
        return Integer.parseInt(binario, 2);
    }
}
