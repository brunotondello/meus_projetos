package projetos.meusprojetos.principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TelaLogin {


    public static void main(String[] args) {
        JFrame telaLogin = new JFrame("Login");
        telaLogin.setSize(300, 250);
        telaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaLogin.setLayout(null);
        telaLogin.setResizable(false); // Não deixa maximizar a janela
        telaLogin.setLocationRelativeTo(null);//Orientação da tela no centro

        JLabel lblMesage = new JLabel("Faça login para acessar o sistema");
        lblMesage.setBounds(-10, 20, 300, 25); // x, y, largura, altura
        lblMesage.setFont(new Font("Courier", Font.BOLD, 14)); // Estilo,
        lblMesage.setForeground(Color.BLACK); // ou qualquer outra cor
        lblMesage.setHorizontalAlignment(SwingConstants.CENTER); // LEFT, RIGHT, CENTER
        lblMesage.setVerticalAlignment(SwingConstants.CENTER);       // TOP, CENTER, BOTTOM
        telaLogin.add(lblMesage);

        // Labels
        JLabel lblUser = new JLabel("Usuário:");
        lblUser.setBounds(30, 65, 60, 25);
        lblUser.setFont(new Font("Courier", Font.PLAIN, 12));
        telaLogin.add(lblUser);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 95, 60, 25);
        lblSenha.setFont(new Font("Courier", Font.PLAIN, 12));
        telaLogin.add(lblSenha);

        // Text Fields
        JTextField txtBoxUser = new JTextField("Digite seu usuário...");
        txtBoxUser.setBounds(90, 65, 150, 25);
        txtBoxUser.setForeground(Color.GRAY); // <- cor inicial do placeholder
        telaLogin.add(txtBoxUser);

        JPasswordField txtBoxSenha = new JPasswordField("Digite sua senha...");
        txtBoxSenha.setBounds(90, 95, 150, 25);
        telaLogin.add(txtBoxSenha);

        //Buttons
        JButton btnLogin = new JButton("Entrar");
        btnLogin.setBounds(100, 145, 100, 30);
        telaLogin.add(btnLogin);

        // Ação do botão
        btnLogin.addActionListener(e -> {
            String usuario = txtBoxUser.getText();
            String senha = txtBoxSenha.getText();

            // Aqui você pode validar o usuário e senha
            if (!usuario.isEmpty()) {
                if (usuario.equals("1") && senha.equals("1")) {
                    telaLogin.dispose(); // Fecha a janela atual
                    new TelaPrincipal(usuario); // Abre a próxima
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Usuário ou senha inválido.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(telaLogin, "Preencha o nome de usuário.", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        });

        telaLogin.setVisible(true);

        txtBoxUser.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBoxUser.getText().equals("Digite seu usuário...")) {
                    txtBoxUser.setText("");
                    txtBoxUser.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBoxUser.getText().isEmpty()) {
                    txtBoxUser.setText("Digite seu usuário...");
                    txtBoxUser.setForeground(Color.GRAY);
                }
            }
        });

        char echoChar = txtBoxSenha.getEchoChar();

        txtBoxSenha.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(txtBoxSenha.getPassword()).equals("Digite sua senha...")) {
                    txtBoxSenha.setEchoChar((char) 1);
                    txtBoxSenha.setText("");
                    txtBoxSenha.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(txtBoxSenha.getPassword()).isEmpty()) {
                    txtBoxSenha.setText("Digite sua senha...");
                    txtBoxSenha.setForeground(Color.GRAY);
                    txtBoxSenha.setEchoChar((char) 0);
                }
            }
        });
    }

}
