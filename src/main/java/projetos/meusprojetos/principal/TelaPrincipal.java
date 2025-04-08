package projetos.meusprojetos.principal;

import projetos.meusprojetos.menu.MeuMenu;

import javax.swing.*;

public class TelaPrincipal {

   public static void main(String[] args) {

        new TelaPrincipal("Bruno Tondello");
    }

    public TelaPrincipal(String usuario) {

        MeuMenu menu = new MeuMenu();

        JFrame telaPrincipal = new JFrame("Bem-vindo");
        telaPrincipal.setSize(1024, 768);
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaPrincipal.setLayout(null);
        telaPrincipal.setResizable(false); // Não deixa maximizar a janela
        telaPrincipal.setLocationRelativeTo(null); //Orientação da tela no centro

        JLabel mensagem = new JLabel("Olá, " + usuario + "! Bem-vindo ao sistema.");
        mensagem.setBounds(50, 50, 300, 25);
        telaPrincipal.add(mensagem);

        telaPrincipal.setJMenuBar(menu.criarMenu());
        telaPrincipal.setVisible(true);
    }

}
