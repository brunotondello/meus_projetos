package projetos.meusprojetos.intermediario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class OrdenadorQuickSort extends JFrame {

    private JTextField inputField;
    private JButton ordenarButton;
    private JLabel resultadoLabel;

    public OrdenadorQuickSort() {
        setTitle("QuickSort Ordenador");
        setSize(500, 180);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        inputField = new JTextField(20);
        ordenarButton = new JButton("Ordenar");
        resultadoLabel = new JLabel("Resultado aparecerá aqui");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Digite os números separados por vírgula:"));
        panel.add(inputField);
        panel.add(ordenarButton);
        panel.add(resultadoLabel);

        ordenarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] partes = inputField.getText().split(",");
                    int[] numeros = new int[partes.length];
                    for (int i = 0; i < partes.length; i++) {
                        numeros[i] = Integer.parseInt(partes[i].trim());
                    }

                    quickSort(numeros, 0, numeros.length - 1);

                    resultadoLabel.setText("Ordenado: " + Arrays.toString(numeros));
                } catch (Exception ex) {
                    resultadoLabel.setText("Erro: verifique a entrada.");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    // QuickSort
    private static void quickSort(int[] array, int inicio, int fim) {
        if (inicio < fim) {
            int indicePivo = particionar(array, inicio, fim);
            quickSort(array, inicio, indicePivo - 1);
            quickSort(array, indicePivo + 1, fim);
        }
    }

    private static int particionar(int[] array, int inicio, int fim) {
        int pivo = array[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (array[j] <= pivo) {
                i++;
                trocar(array, i, j);
            }
        }
        trocar(array, i + 1, fim);
        return i + 1;
    }

    private static void trocar(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
