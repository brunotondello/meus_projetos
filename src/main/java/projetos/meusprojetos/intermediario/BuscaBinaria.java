package projetos.meusprojetos.intermediario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscaBinaria extends JFrame {

    private JTextField campoValor;
    private JLabel resultadoLabel;

    // Array ordenado fixo (poderia vir de um campo também)
    private int[] numeros = {1, 3, 5, 7, 9, 11, 13};

    public BuscaBinaria() {
        setTitle("Busca Binária");
        setSize(400, 200);
        setResizable(false); // Impede redimensionamento da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JLabel instrucao = new JLabel("Digite o número que deseja buscar:");
        add(instrucao);

        campoValor = new JTextField();
        add(campoValor);

        JButton botaoBuscar = new JButton("Buscar");
        add(botaoBuscar);

        resultadoLabel = new JLabel("Resultado aparecerá aqui.");
        resultadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(resultadoLabel);

        // Ação do botão
        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int valor = Integer.parseInt(campoValor.getText());
                    int resultado = buscaBinaria(numeros, valor);

                    if (resultado != -1) {
                        resultadoLabel.setText("Número encontrado na posição: " + resultado);
                    } else {
                        resultadoLabel.setText("Número não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    resultadoLabel.setText("Por favor, digite um número válido.");
                }
            }
        });
        setVisible(true);
    }

    // Método da busca binária
    private int buscaBinaria(int[] array, int valor) {
        int inicio = 0;
        int fim = array.length - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (array[meio] == valor) {
                return meio;
            } else if (array[meio] < valor) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }

        return -1;
    }
}
