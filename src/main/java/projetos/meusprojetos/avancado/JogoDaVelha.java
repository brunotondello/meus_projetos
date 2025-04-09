package projetos.meusprojetos.avancado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class JogoDaVelha extends JFrame implements ActionListener {

    private JButton[] botoes = new JButton[9];
    private char jogadorAtual = 'X';
    private boolean jogoAtivo = true;
    private JLabel status;
    private JButton botaoReiniciar;
    private JLabel placar;
    private JComboBox<String> seletorDificuldade;
    private JComboBox<String> seletorModo;
    private int vitoriasX = 0;
    private int vitoriasO = 0;

    private static final int[][] COMBINACOES_VITORIA = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };

    public JogoDaVelha() {
        setTitle("Jogo da Velha - Java Swing");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelJogo = new JPanel(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            botoes[i] = new JButton("");
            botoes[i].setFont(new Font("Arial", Font.BOLD, 60));
            botoes[i].addActionListener(this);
            painelJogo.add(botoes[i]);
        }

        status = new JLabel("Vez do jogador: X", SwingConstants.CENTER);
        status.setFont(new Font("Arial", Font.PLAIN, 20));
        add(status, BorderLayout.NORTH);

        // Botões
        botaoReiniciar = new JButton("Reiniciar Jogo");
        botaoReiniciar.setFont(new Font("Arial", Font.PLAIN, 18));
        botaoReiniciar.addActionListener(e -> reiniciarJogo());

        JButton botaoZerar = new JButton("Zerar Placar");
        botaoZerar.setFont(new Font("Arial", Font.PLAIN, 18));
        botaoZerar.addActionListener(e -> {
            vitoriasX = 0;
            vitoriasO = 0;
            atualizarPlacar();
        });

        placar = new JLabel("Vitórias - X: 0 | O: 0", SwingConstants.CENTER);
        placar.setFont(new Font("Arial", Font.PLAIN, 18));

        seletorDificuldade = new JComboBox<>(new String[]{"Fácil", "Médio", "Difícil"});
        seletorDificuldade.setFont(new Font("Arial", Font.PLAIN, 16));

        seletorModo = new JComboBox<>(new String[]{"1 Jogador", "2 Jogadores"});
        seletorModo.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel botoesControle = new JPanel(new GridLayout(1, 2));
        botoesControle.add(botaoReiniciar);
        botoesControle.add(botaoZerar);

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.add(seletorDificuldade, BorderLayout.NORTH);
        painelInferior.add(botoesControle, BorderLayout.CENTER);
        painelInferior.add(placar, BorderLayout.SOUTH);
        painelInferior.add(seletorModo, BorderLayout.WEST);

        add(painelJogo, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jogoAtivo) return;

        JButton clicado = (JButton) e.getSource();

        if (!clicado.getText().equals("")) return;

        clicado.setText(String.valueOf(jogadorAtual));
        clicado.setEnabled(false);

        if (verificarVitoria()) {
            status.setText("🎉 Jogador " + jogadorAtual + " venceu!");
            if (jogadorAtual == 'X') vitoriasX++;
            else vitoriasO++;
            atualizarPlacar();
            jogoAtivo = false;
        } else if (tabuleiroCheio()) {
            status.setText("😬 Empate!");
            jogoAtivo = false;
        } else {
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
            status.setText("Vez do jogador: " + jogadorAtual);
            if (jogadorAtual == 'O' && seletorModo.getSelectedItem().equals("1 Jogador")) {
                jogarIA();
            }
        }
    }

    private void jogarIA() {
        String dificuldade = (String) seletorDificuldade.getSelectedItem();
        switch (dificuldade) {
            case "Fácil": jogadaAleatoria(); break;
            case "Médio": jogadaMedia(); break;
            case "Difícil": jogadaMinimax(); break;
        }

        if (verificarVitoria()) {
            status.setText("🎉 Jogador O venceu!");
            vitoriasO++;
            atualizarPlacar();
            jogoAtivo = false;
        } else if (tabuleiroCheio()) {
            status.setText("😬 Empate!");
            jogoAtivo = false;
        } else {
            jogadorAtual = 'X';
            status.setText("Vez do jogador: X");
        }
    }

    private void jogadaAleatoria() {
        List<Integer> livres = new ArrayList<>();
        for (int i = 0; i < botoes.length; i++) {
            if (botoes[i].getText().equals("")) livres.add(i);
        }
        if (!livres.isEmpty()) {
            int escolha = livres.get(new Random().nextInt(livres.size()));
            botoes[escolha].setText("O");
            botoes[escolha].setEnabled(false);
        }
    }

    private void jogadaMedia() {
        if (!tentarJogada("O")) {
            if (!tentarJogada("X")) {
                jogadaAleatoria();
            }
        }
    }

    private boolean tentarJogada(String jogador) {
        for (int i = 0; i < botoes.length; i++) {
            if (botoes[i].getText().equals("")) {
                botoes[i].setText(jogador);
                boolean venceu = verificarVitoria();
                botoes[i].setText("");
                if (venceu) {
                    botoes[i].setText("O");
                    botoes[i].setEnabled(false);
                    return true;
                }
            }
        }
        return false;
    }

    private void jogadaMinimax() {
        int melhorValor = Integer.MIN_VALUE;
        int melhorJogada = -1;

        for (int i = 0; i < botoes.length; i++) {
            if (botoes[i].getText().equals("")) {
                botoes[i].setText("O");
                int valor = minimax(false);
                botoes[i].setText("");
                if (valor > melhorValor) {
                    melhorValor = valor;
                    melhorJogada = i;
                }
            }
        }

        if (melhorJogada != -1) {
            botoes[melhorJogada].setText("O");
            botoes[melhorJogada].setEnabled(false);
        }
    }

    private int minimax(boolean isMax) {
        if (verificarVitoriaSimulado("O")) return 1;
        if (verificarVitoriaSimulado("X")) return -1;
        if (tabuleiroCheio()) return 0;

        int melhorValor = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < botoes.length; i++) {
            if (botoes[i].getText().equals("")) {
                botoes[i].setText(isMax ? "O" : "X");
                int valor = minimax(!isMax);
                botoes[i].setText("");
                melhorValor = isMax ? Math.max(valor, melhorValor) : Math.min(valor, melhorValor);
            }
        }
        return melhorValor;
    }

    private boolean verificarVitoriaSimulado(String jogador) {
        for (int[] c : COMBINACOES_VITORIA) {
            String b1 = botoes[c[0]].getText();
            String b2 = botoes[c[1]].getText();
            String b3 = botoes[c[2]].getText();
            if (b1.equals(jogador) && b2.equals(jogador) && b3.equals(jogador)) {
                return true;
            }
        }
        return false;
    }

    private int[] getCombinacaoVencedora() {
        for (int[] c : COMBINACOES_VITORIA) {
            String b1 = botoes[c[0]].getText();
            String b2 = botoes[c[1]].getText();
            String b3 = botoes[c[2]].getText();
            if (!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                return c;
            }
        }
        return null;
    }

    private boolean verificarVitoria() {
        int[] combinacao = getCombinacaoVencedora();
        if (combinacao != null) {
            for (int i : combinacao) {
                botoes[i].setBackground(Color.GREEN);
            }
            return true;
        }
        return false;
    }

    private boolean tabuleiroCheio() {
        for (JButton b : botoes) {
            if (b.getText().equals("")) return false;
        }
        return true;
    }

    private void atualizarPlacar() {
        placar.setText("Vitórias - X: " + vitoriasX + " | O: " + vitoriasO);
    }

    private void reiniciarJogo() {
        for (int i = 0; i < botoes.length; i++) {
            botoes[i].setText("");
            botoes[i].setEnabled(true);
            botoes[i].setBackground(UIManager.getColor("Button.background"));
        }
        jogadorAtual = 'X';
        jogoAtivo = true;
        status.setText("Vez do jogador: X");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JogoDaVelha jogo = new JogoDaVelha();
            jogo.setVisible(true);
        });
    }
}
