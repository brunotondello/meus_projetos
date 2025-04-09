package projetos.meusprojetos.testes;

import projetos.meusprojetos.iniciante.CalcularFatoriais;
import projetos.meusprojetos.iniciante.InversorDePalavras;
import projetos.meusprojetos.iniciante.SomadorDeDigitos;
import projetos.meusprojetos.iniciante.VerificadorDeNumerosPrimos;
import projetos.meusprojetos.intermediario.VerificadorDePalindromo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MeuMenuSwing extends JFrame {

    public MeuMenuSwing() {
        setTitle("Menu Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnFatorial = new JButton("Calcular Fatorial");
        JButton btnInversor = new JButton("Inverter Palavra");
        JButton btnPrimo = new JButton("Verificar Número Primo");
        JButton btnSomaDigitos = new JButton("Somar Dígitos");
        JButton btnPalindromo = new JButton("Verificar Palíndromo");

        btnFatorial.addActionListener((ActionEvent e) -> new CalcularFatoriais());
        btnInversor.addActionListener((ActionEvent e) -> new InversorDePalavras());
        btnPrimo.addActionListener((ActionEvent e) -> new VerificadorDeNumerosPrimos());
        btnSomaDigitos.addActionListener((ActionEvent e) -> new SomadorDeDigitos());
        btnPalindromo.addActionListener((ActionEvent e) -> new VerificadorDePalindromo());

        panel.add(btnFatorial);
        panel.add(btnInversor);
        panel.add(btnPrimo);
        panel.add(btnSomaDigitos);
        panel.add(btnPalindromo);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MeuMenuSwing().setVisible(true));
    }
}

