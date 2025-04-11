package projetos.meusprojetos.avancado.jogodavelha;

import javax.swing.*;
import java.awt.*;

public class JanelaJogo extends JFrame {
    private Tabuleiro tabuleiro;

    public JanelaJogo() {
        setTitle("Jogo da Velha");
        setSize(400, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false); // Não deixa maximizar a janela
        setLocationRelativeTo(null);//Orientação da tela no centro

        JLabel status = new JLabel("Vez do jogador: X", SwingConstants.CENTER);
        status.setFont(new Font("Arial", Font.PLAIN, 20));

        JLabel placar = new JLabel("Vitórias - X: 0 | O: 0", SwingConstants.CENTER);
        placar.setFont(new Font("Arial", Font.PLAIN, 18));

        JComboBox<String> seletorModo = new JComboBox<>(new String[]{"1 Jogador", "2 Jogadores"});
        seletorModo.setFont(new Font("Arial", Font.PLAIN, 16));

        JComboBox<String> seletorDificuldade = new JComboBox<>(new String[]{"Fácil", "Médio", "Difícil"});
        seletorDificuldade.setFont(new Font("Arial", Font.PLAIN, 16));

        tabuleiro = new Tabuleiro(status, placar, seletorModo, seletorDificuldade);

        JPanel painelInferior = new JPanel(new BorderLayout());
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(seletorModo, BorderLayout.WEST);
        painelTopo.add(seletorDificuldade, BorderLayout.EAST);

        JButton botaoReiniciar = new JButton("Reiniciar Jogo");
        botaoReiniciar.addActionListener(e -> tabuleiro.reiniciar());

        JButton botaoResetarPlacar = new JButton("Resetar Placar");
        botaoResetarPlacar.addActionListener(e -> {
            tabuleiro.resetarPlacar();
            tabuleiro.atualizarPlacar();
        });

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(botaoReiniciar);
        painelBotoes.add(botaoResetarPlacar);

        painelInferior.add(painelTopo, BorderLayout.NORTH);
        painelInferior.add(painelBotoes, BorderLayout.CENTER);
        painelInferior.add(placar, BorderLayout.SOUTH);

        add(status, BorderLayout.NORTH);
        add(tabuleiro.criarTabuleiro(), BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
    }
}
