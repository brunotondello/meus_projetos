package projetos.meusprojetos.avancado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Random;

public class EncurtadorDeURL extends JFrame {

    private HashMap<String, String> bancoDeURLs = new HashMap<>();
    private JTextField campoURLLonga;
    private JTextField campoURLEncurtada;
    private JTextField campoConsulta;
    private JLabel resultadoConsulta;

    public EncurtadorDeURL() {
        setTitle("Encurtador de URL");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        // Painel para encurtar
        JPanel painelEncurtar = new JPanel(new FlowLayout());
        campoURLLonga = new JTextField(25);
        JButton botaoEncurtar = new JButton("Encurtar");
        campoURLEncurtada = new JTextField(15);
        campoURLEncurtada.setEditable(false);

        painelEncurtar.add(new JLabel("URL Longa:"));
        painelEncurtar.add(campoURLLonga);
        painelEncurtar.add(botaoEncurtar);
        painelEncurtar.add(new JLabel("Código:"));
        painelEncurtar.add(campoURLEncurtada);

        // Ação do botão Encurtar
        botaoEncurtar.addActionListener((ActionEvent e) -> {
            String urlLonga = campoURLLonga.getText();
            if (!urlLonga.isBlank()) {
                String codigo = gerarCodigo();
                bancoDeURLs.put(codigo, urlLonga);
                campoURLEncurtada.setText(codigo);
            }
        });

        // Painel para consultar
        JPanel painelConsulta = new JPanel(new FlowLayout());
        campoConsulta = new JTextField(10);
        JButton botaoConsultar = new JButton("Consultar");
        resultadoConsulta = new JLabel(" ");

        painelConsulta.add(new JLabel("Código:"));
        painelConsulta.add(campoConsulta);
        painelConsulta.add(botaoConsultar);
        painelConsulta.add(resultadoConsulta);

        botaoConsultar.addActionListener((ActionEvent e) -> {
            String codigo = campoConsulta.getText();
            String url = bancoDeURLs.getOrDefault(codigo, "Código não encontrado.");
            resultadoConsulta.setText(url);
        });

        add(painelEncurtar);
        add(painelConsulta);
    }

    // Gera um código aleatório de 6 caracteres
    private String gerarCodigo() {
        String caracteres = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(caracteres.charAt(rand.nextInt(caracteres.length())));
        }
        return sb.toString();
    }
}

