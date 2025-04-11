package projetos.meusprojetos.avancado.jogodavelha;

import javax.swing.*;

public class JogoDaVelha  {

    public JogoDaVelha(){
        SwingUtilities.invokeLater(() -> {
            JanelaJogo janela = new JanelaJogo();
            janela.setVisible(true);
        });
    }
}

