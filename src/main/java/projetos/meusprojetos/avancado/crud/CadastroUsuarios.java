package projetos.meusprojetos.avancado.crud;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroUsuarios extends JFrame {

    private List<Usuario> listaUsuarios = new ArrayList<>();
    private int proximoId = 1;

    public CadastroUsuarios() {
        setTitle("Sistema de Cadastro de Usuários");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnRemover = new JButton("Remover");

        btnCadastrar.addActionListener(e -> cadastrarUsuario());
        btnListar.addActionListener(e -> listarUsuarios());
        btnAtualizar.addActionListener(e -> atualizarUsuario());
        btnRemover.addActionListener(e -> removerUsuario());

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        panel.add(btnCadastrar);
        panel.add(btnListar);
        panel.add(btnAtualizar);
        panel.add(btnRemover);

        add(panel);
        setVisible(true);
    }

    private void cadastrarUsuario() {
        String nome = JOptionPane.showInputDialog(this, "Digite o nome:");
        String email = JOptionPane.showInputDialog(this, "Digite o e-mail:");

        if (nome != null && email != null) {
            Usuario u = new Usuario(proximoId++, nome, email);
            listaUsuarios.add(u);
            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        }
    }

    private void listarUsuarios() {
        if (listaUsuarios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum usuário cadastrado.");
        } else {
            StringBuilder lista = new StringBuilder();
            for (Usuario u : listaUsuarios) {
                lista.append(u).append("\n");
            }
            JTextArea textArea = new JTextArea(lista.toString());
            textArea.setEditable(false);
            JScrollPane scroll = new JScrollPane(textArea);
            scroll.setPreferredSize(new Dimension(350, 200));
            JOptionPane.showMessageDialog(this, scroll, "Lista de Usuários", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void atualizarUsuario() {
        String idStr = JOptionPane.showInputDialog(this, "Digite o ID do usuário:");
        try {
            int id = Integer.parseInt(idStr);
            Usuario u = buscarUsuarioPorId(id);
            if (u != null) {
                String novoNome = JOptionPane.showInputDialog(this, "Novo nome:", u.getNome());
                String novoEmail = JOptionPane.showInputDialog(this, "Novo e-mail:", u.getEmail());
                u.setNome(novoNome);
                u.setEmail(novoEmail);
                JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void removerUsuario() {
        String idStr = JOptionPane.showInputDialog(this, "Digite o ID do usuário:");
        try {
            int id = Integer.parseInt(idStr);
            Usuario u = buscarUsuarioPorId(id);
            if (u != null) {
                listaUsuarios.remove(u);
                JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private Usuario buscarUsuarioPorId(int id) {
        for (Usuario u : listaUsuarios) {
            if (u.getId() == id) return u;
        }
        return null;
    }
}


