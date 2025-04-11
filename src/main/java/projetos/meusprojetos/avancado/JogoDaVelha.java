package projetos.meusprojetos.avancado;

import projetos.meusprojetos.avancado.jogodavelha.JanelaJogo;

import javax.swing.*;

public class JogoDaVelha  {

    public JogoDaVelha(){
        SwingUtilities.invokeLater(() -> {
            JanelaJogo janela = new JanelaJogo();
            janela.setVisible(true);
        });
    }
}

