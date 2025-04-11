package projetos.meusprojetos.avancado.jogodavelha;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IA {

    public static int escolherJogada(JButton[] botoes, String dificuldade) {
        switch (dificuldade) {
            case "Fácil":
                return jogadaAleatoria(botoes);
            case "Médio":
                return jogadaMedia(botoes);
            case "Difícil":
                return jogadaMinimax(botoes);
        }
        return jogadaAleatoria(botoes);
    }

    private static int jogadaAleatoria(JButton[] botoes) {
        List<Integer> livres = new ArrayList<>();
        for (int i = 0; i < botoes.length; i++) {
            if (botoes[i].getText().equals("")) livres.add(i);
        }
        return livres.get(new Random().nextInt(livres.size()));
    }

    private static int jogadaMedia(JButton[] botoes) {
        for (int i = 0; i < botoes.length; i++) {
            if (botoes[i].getText().equals("")) {
                botoes[i].setText("O");
                if (verificaVitoria(botoes, "O")) {
                    botoes[i].setText("");
                    return i;
                }
                botoes[i].setText("");
            }
        }
        for (int i = 0; i < botoes.length; i++) {
            if (botoes[i].getText().equals("")) {
                botoes[i].setText("X");
                if (verificaVitoria(botoes, "X")) {
                    botoes[i].setText("");
                    return i;
                }
                botoes[i].setText("");
            }
        }
        return jogadaAleatoria(botoes);
    }

    private static int jogadaMinimax(JButton[] botoes) {
        int melhorValor = Integer.MIN_VALUE;
        int melhorJogada = -1;

        for (int i = 0; i < botoes.length; i++) {
            if (botoes[i].getText().equals("")) {
                botoes[i].setText("O");
                int valor = minimax(botoes, false);
                botoes[i].setText("");
                if (valor > melhorValor) {
                    melhorValor = valor;
                    melhorJogada = i;
                }
            }
        }
        return melhorJogada;
    }

    private static int minimax(JButton[] botoes, boolean isMax) {
        if (verificaVitoria(botoes, "O")) return 1;
        if (verificaVitoria(botoes, "X")) return -1;
        if (Arrays.stream(botoes).allMatch(b -> !b.getText().equals(""))) return 0;

        int melhorValor = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < botoes.length; i++) {
            if (botoes[i].getText().equals("")) {
                botoes[i].setText(isMax ? "O" : "X");
                int valor = minimax(botoes, !isMax);
                botoes[i].setText("");
                melhorValor = isMax ? Math.max(valor, melhorValor) : Math.min(valor, melhorValor);
            }
        }
        return melhorValor;
    }

    private static boolean verificaVitoria(JButton[] botoes, String jogador) {
        int[][] c = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for (int[] comb : c) {
            if (botoes[comb[0]].getText().equals(jogador) &&
                    botoes[comb[1]].getText().equals(jogador) &&
                    botoes[comb[2]].getText().equals(jogador)) {
                return true;
            }
        }
        return false;
    }
}

