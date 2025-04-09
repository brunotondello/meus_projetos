package projetos.meusprojetos.iniciante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FizzBuzz extends JFrame {

    private JTextArea textArea;
    private JButton botaoExecutar;
    private volatile boolean executando = false;
    private Thread thread; // <<< SEGURA A THREAD PRA PARAR DEPOIS

    public FizzBuzz() {

        setTitle("FizzBuzz em Java");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Criar área de texto
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Criar botão
        botaoExecutar = new JButton("Executar FizzBuzz");
        botaoExecutar.addActionListener(e -> executarFizzBuzz());

        add(scrollPane, BorderLayout.CENTER);
        add(botaoExecutar, BorderLayout.SOUTH);

        // Adiciona listener para parar a thread ao fechar a janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                executando = false;
                if (thread != null && thread.isAlive()) {
                    thread.interrupt(); // para a thread com segurança
                    System.out.println("Thread interrompida ao fechar a janela.");
                }
            }
        });

        setVisible(true);
    }

    public void executarFizzBuzz() {
        botaoExecutar.setEnabled(false);
        textArea.setText("Executando o programa FizzBuzz, por favor aguarde!\n");
        executando = true;

        thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 1; i <= 100 && executando; i++) {
                    final int num = i;

                    SwingUtilities.invokeLater(() -> {
                        if (!executando) return;
                        if (num % 3 == 0 && num % 5 == 0) {
                            textArea.append(num + " - FizzBuzz\n");
                        } else if (num % 3 == 0) {
                            textArea.append(num + " - Fizz\n");
                        } else if (num % 5 == 0) {
                            textArea.append(num + " - Buzz\n");
                        } else {
                            textArea.append(num + "\n");
                        }
                    });

                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                SwingUtilities.invokeLater(() -> textArea.append("Execução interrompida.\n"));
            } finally {
                executando = false;
                SwingUtilities.invokeLater(() -> botaoExecutar.setEnabled(true));
            }
        });

        thread.start();
    }
}
