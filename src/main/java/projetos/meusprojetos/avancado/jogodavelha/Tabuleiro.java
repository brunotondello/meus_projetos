package projetos.meusprojetos.avancado.jogodavelha;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tabuleiro {
    private JButton[] botoes = new JButton[9];
    private char jogadorAtual = 'X';
    private boolean jogoAtivo = true;
    private int vitoriasX = 0;
    private int vitoriasO = 0;
    private JLabel status;
    private JLabel placar;
    private JComboBox<String> seletorModo;
    private JComboBox<String> seletorDificuldade;

    public Tabuleiro(JLabel status, JLabel placar, JComboBox<String> seletorModo, JComboBox<String> seletorDificuldade) {
        this.status = status;
        this.placar = placar;
        this.seletorModo = seletorModo;
        this.seletorDificuldade = seletorDificuldade;
    }

    public JPanel criarTabuleiro() {
        JPanel painel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            JButton botao = new JButton("");
            botao.setFont(new Font("Arial", Font.BOLD, 60));
            final int index = i;
            botao.addActionListener(e -> realizarJogada(index));
            botoes[i] = botao;
            painel.add(botao);
        }
        return painel;
    }

    private void realizarJogada(int index) {
        if (!jogoAtivo || !botoes[index].getText().equals("")) return;

        botoes[index].setText(String.valueOf(jogadorAtual));
        botoes[index].setEnabled(false);

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
        int[][] combinacoes = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };
        for (int[] c : combinacoes) {
            if (botoes[c[0]].getText().equals(jogador) &&
                    botoes[c[1]].getText().equals(jogador) &&
                    botoes[c[2]].getText().equals(jogador)) {
                return true;
            }
        }
        return false;
    }

    private boolean verificarVitoria() {
        int[][] combinacoes = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };
        for (int[] c : combinacoes) {
            String b1 = botoes[c[0]].getText();
            String b2 = botoes[c[1]].getText();
            String b3 = botoes[c[2]].getText();
            if (!b1.equals("") && b1.equals(b2) && b2.equals(b3)) {
                for (int i : c) {
                    botoes[i].setBackground(Color.GREEN);
                }
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

    public void reiniciar() {
        for (JButton b : botoes) {
            b.setText("");
            b.setEnabled(true);
            b.setBackground(null);
        }
        jogadorAtual = 'X';
        jogoAtivo = true;
        status.setText("Vez do jogador: X");
    }

    public void resetarPlacar() {
        vitoriasX = 0;
        vitoriasO = 0;
    }

    public void atualizarPlacar() {
        placar.setText("Vitórias - X: " + vitoriasX + " | O: " + vitoriasO);
    }
}
