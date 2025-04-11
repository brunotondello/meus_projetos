package projetos.meusprojetos.avancado.interpretadordeexpressoes;

import javax.swing.*;
import java.awt.*;

    public class InterpretadorExpressao extends JFrame {

        private JTextField campoExpressao;
        private JTextArea resultadoArea;

        public InterpretadorExpressao() {
            setTitle("Interpretador de Expressões Matemáticas");
            setSize(400, 300);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setLayout(new BorderLayout());

            campoExpressao = new JTextField();
            JButton botaoCalcular = new JButton("Calcular");
            resultadoArea = new JTextArea();
            resultadoArea.setEditable(false);

            botaoCalcular.addActionListener(e -> {
                String expressao = campoExpressao.getText();
                try {
                    double resultado = UtilExpressao.avaliar(expressao);
                    resultadoArea.setText("Resultado: " + resultado);
                } catch (Exception ex) {
                    resultadoArea.setText("Erro: " + ex.getMessage());
                }
            });

            JPanel painelTopo = new JPanel(new BorderLayout());
            painelTopo.add(new JLabel("Digite a expressão:"), BorderLayout.NORTH);
            painelTopo.add(campoExpressao, BorderLayout.CENTER);
            painelTopo.add(botaoCalcular, BorderLayout.EAST);

            add(painelTopo, BorderLayout.NORTH);
            add(new JScrollPane(resultadoArea), BorderLayout.CENTER);

            setVisible(true);
        }
    }