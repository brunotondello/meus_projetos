package projetos.meusprojetos.avancado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class JogoDaVelha extends JFrame implements ActionListener {

    private JButton[] botoes = new JButton[9];
    private char jogadorAtual = 'X';
    private boolean jogoAtivo = true;
    private JLabel status;
    private JButton botaoReiniciar;

    public JogoDaVelha() {
        setTitle("Jogo da Velha - Java");
        setSize(400, 450);
        setResizable(false); // Impede redimensionamento da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel dos botões (tabuleiro)
        JPanel painelJogo = new JPanel();
        painelJogo.setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            botoes[i] = new JButton("");
            botoes[i].setFont(new Font("Arial", Font.BOLD, 60));
            botoes[i].addActionListener(this);
            painelJogo.add(botoes[i]);
        }

        // Status do jogo
        status = new JLabel("Vez do jogador: X", SwingConstants.CENTER);
        status.setFont(new Font("Arial", Font.PLAIN, 20));
        add(status, BorderLayout.NORTH);
        add(painelJogo, BorderLayout.CENTER);

        // Botão de Reiniciar
        botaoReiniciar = new JButton("Reiniciar");
        botaoReiniciar.setFont(new Font("Arial", Font.PLAIN, 18));
        botaoReiniciar.addActionListener(e -> reiniciarJogo());

        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new BorderLayout());
        painelInferior.add(botaoReiniciar, BorderLayout.CENTER);

        add(painelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (!jogoAtivo) return;

        JButton clicado = (JButton) e.getSource();

        if (!clicado.getText().equals("")) return; // já clicado

        clicado.setText(String.valueOf(jogadorAtual));
        clicado.setEnabled(false);

        if (verificarVitoria()) {
            status.setText("Jogador " + jogadorAtual + " venceu!");
            jogoAtivo = false;
        } else if (tabuleiroCheio()) {
            status.setText("Empate!");
            jogoAtivo = false;
        } else {
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
            status.setText("Vez do jogador: " + jogadorAtual);
        }
    }

    private boolean verificarVitoria() {
        int[][] vitorias = {
                {0,1,2}, {3,4,5}, {6,7,8}, // linhas
                {0,3,6}, {1,4,7}, {2,5,8}, // colunas
                {0,4,8}, {2,4,6}           // diagonais
        };

        for (int[] comb : vitorias) {
            String b1 = botoes[comb[0]].getText();
            String b2 = botoes[comb[1]].getText();
            String b3 = botoes[comb[2]].getText();

            if (!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                botoes[comb[0]].setBackground(Color.GREEN);
                botoes[comb[1]].setBackground(Color.GREEN);
                botoes[comb[2]].setBackground(Color.GREEN);
                return true;
            }
        }
        return false;
    }

    private boolean tabuleiroCheio() {
        for (JButton b : botoes) {
            if (b.getText().equals("")) return false;
        }
        return true;
    }

    private void reiniciarJogo() {
        for (int i = 0; i < botoes.length; i++) {
            botoes[i].setText("");
            botoes[i].setEnabled(true);
            botoes[i].setBackground(null); // remove cor de vitória
        }
        jogadorAtual = 'X';
        jogoAtivo = true;
        status.setText("Vez do jogador: " + jogadorAtual);
    }


 /*   public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JogoDaVelha jogo = new JogoDaVelha();
            jogo.setVisible(true);
        });
    }

    char[] tabuleiro = {'1','2','3','4','5','6','7','8','9'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] tabuleiro = {'1','2','3','4','5','6','7','8','9'};
        char jogadorAtual = 'X';
        int jogadas = 0;

        while (true) {
            mostrarTabuleiro(tabuleiro);
            System.out.print("Jogador " + jogadorAtual + ", escolha uma posição (1-9): ");
            int pos;

            try {
                pos = scanner.nextInt() - 1;
                if (pos < 0 || pos > 8 || tabuleiro[pos] == 'X' || tabuleiro[pos] == 'O') {
                    System.out.println("Posição inválida ou já ocupada. Tente novamente.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida.");
                scanner.next(); // limpar scanner
                continue;
            }

            tabuleiro[pos] = jogadorAtual;
            jogadas++;

            if (verificarVitoria(tabuleiro, jogadorAtual)) {
                mostrarTabuleiro(tabuleiro);
                System.out.println("🎉 Jogador " + jogadorAtual + " venceu!");
                break;
            }

            if (jogadas == 9) {
                mostrarTabuleiro(tabuleiro);
                System.out.println("😬 Empate!");
                break;
            }

            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }


    public static void mostrarTabuleiro(char[] tabuleiro) {
        System.out.println();
        System.out.println(" " + tabuleiro[0] + " | " + tabuleiro[1] + " | " + tabuleiro[2]);
        System.out.println("---+---+---");
        System.out.println(" " + tabuleiro[3] + " | " + tabuleiro[4] + " | " + tabuleiro[5]);
        System.out.println("---+---+---");
        System.out.println(" " + tabuleiro[6] + " | " + tabuleiro[7] + " | " + tabuleiro[8]);
        System.out.println();
    }

    public static boolean verificarVitoria(char[] tabuleiro, char jogador) {
        int[][] combinacoes = {
                {0,1,2},{3,4,5},{6,7,8}, // linhas
                {0,3,6},{1,4,7},{2,5,8}, // colunas
                {0,4,8},{2,4,6}          // diagonais
        };

        for (int[] c : combinacoes) {
            if (tabuleiro[c[0]] == jogador && tabuleiro[c[1]] == jogador && tabuleiro[c[2]] == jogador) {
                return true;
            }
        }
        return false;
    }*/
}
