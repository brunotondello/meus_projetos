package projetos.meusprojetos.menu;

import projetos.meusprojetos.iniciantes.FizzBuzz;

import javax.swing.*;
import java.awt.*;

public class MeuMenu {

    public JMenuBar criarMenu() {
        // Criar a barra do menu
        JMenuBar barraMenu = new JMenuBar(); //Criar a barra menu

        // Menu "Inicio"
        JMenu menuInicio = new JMenu("Inicio");// Criar o primeiro item da barra
        JMenuItem itemSair = new JMenuItem("Sair"); // criar o primeiro item do menu inicio

        itemSair.addActionListener(e -> encerrarAplicacao());
        menuInicio.add(itemSair);

        // Menu Iniciante
        JMenu menuIniciante = new JMenu("Iniciante");// Criar o primeiro item da barra
        JMenuItem itemFizzBuzz = new JMenuItem("FizzBuzz");

        // Adiciona itens no menu Iniciante
        itemFizzBuzz.addActionListener(e -> new FizzBuzz());
        menuIniciante.add(itemFizzBuzz);

        // Menu "ajuda"
        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemSobre = new JMenuItem("Sobre");

        // Adiciona itens no menu Sobre
        itemSobre.addActionListener(e -> mostrarSobre());
        menuAjuda.add(itemSobre);

        // Adiciona os menus na barra
        barraMenu.add(menuInicio);
        barraMenu.add(menuIniciante);
        barraMenu.add(menuAjuda);

        return barraMenu;
    }

    private void mostrarSobre() {
//        itemSobre.addActionListener(e ->
        JOptionPane.showMessageDialog(null,
                "Sistema de exemplo\nCriado por Bruno Tondello", "Sobre",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void encerrarAplicacao() {

        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja sair?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {

            JFrame telaFechar = new JFrame("Encerrando");
            telaFechar.setSize(300, 120);
//        telaFechar.setLayout(new FlowLayout(FlowLayout.CENTER));
//        telaFechar.setResizable(false); // Não deixa maximizar a janela
            telaFechar.setLayout(new GridBagLayout());
            telaFechar.setLocationRelativeTo(null);
            telaFechar.setUndecorated(true); // remove tudo: fechar, minimizar, maximizar
            telaFechar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel("Fechando em 3...");
            label.setFont(new Font("Arial", Font.BOLD, 14));
            telaFechar.add(label);
            telaFechar.setVisible(true);

            // Timer que atualiza o texto a cada 1 segundo
            Timer timer = new Timer(1000, null);
            final int[] segundos = {4};

            timer.addActionListener(e -> {
                segundos[0]--;
                if (segundos[0] > 0) {
                    label.setText("Fechando em " + segundos[0] + "...");
                } else {
                    timer.stop();
                    telaFechar.dispose(); // fecha a janela
                    System.exit(0);  // encerra o programa
                }
            });

            timer.setInitialDelay(0); // já começa na hora
            timer.start();

        }
    }
}
