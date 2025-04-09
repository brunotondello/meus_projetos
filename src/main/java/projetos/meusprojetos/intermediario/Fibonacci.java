// Pacote onde o projeto está localizado
package projetos.meusprojetos.intermediario;

// Importação de bibliotecas do Swing para criar a interface gráfica
import javax.swing.*;

// Importações de classes gráficas adicionais
import java.awt.*;

// Importa BigInteger para lidar com números grandes na sequência de Fibonacci
import java.math.BigInteger;

// Importa Consumer para usar expressões lambda com parâmetros
import java.util.function.Consumer;

// Classe principal que herda de JFrame (janela gráfica)
public class Fibonacci extends JFrame {

    // Método main: ponto de entrada da aplicação
    public static void main(String[] args) {
        new Fibonacci(); // Cria uma instância da janela e exibe
    }

    // Construtor da classe
    public Fibonacci() {
        setTitle("Sequência de Fibonacci"); // Define o título da janela

        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Fecha só essa janela ao clicar no X
        setSize(400, 500); // Define o tamanho da janela
        setResizable(false); // Impede redimensionamento da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BorderLayout()); // Usa BorderLayout para organizar os componentes

        // Cria uma área de texto para exibir os números
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false); // Usuário não pode editar o conteúdo
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Fonte monoespaçada para alinhar os números
        textArea.setMargin(new Insets(10, 10, 10, 10)); // Define margens internas
        JScrollPane scrollPane = new JScrollPane(textArea); // Adiciona barra de rolagem

        // Cria os botões para escolha do modo
        JButton manualButton = new JButton("Entrada Manual"); // Para o usuário escolher a quantidade
        JButton autoButton = new JButton("Automático (15 números)"); // Executa automaticamente com 15 números

        // Label de status na parte inferior da tela
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto da label

        // Painel para organizar os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(manualButton);
        buttonPanel.add(autoButton);

        // Adiciona os painéis à janela principal
        add(buttonPanel, BorderLayout.NORTH); // Painel com os botões no topo
        add(scrollPane, BorderLayout.CENTER); // Área de texto central
        add(statusLabel, BorderLayout.SOUTH); // Label de status na parte inferior

        // Lambda que recebe um número e gera Fibonacci em uma thread separada
        Consumer<Integer> gerarComLoading = (n) -> {
            statusLabel.setText("Calculando..."); // Exibe status de carregamento
            textArea.setText(""); // Limpa o conteúdo anterior

            // Inicia nova thread para não travar a interface
            new Thread(() -> {
                String resultado = gerarFibonacci(n); // Gera a sequência de Fibonacci
                // Atualiza a interface de forma segura (na thread da interface)
                SwingUtilities.invokeLater(() -> {
                    textArea.setText(resultado); // Mostra o resultado no textArea
                    statusLabel.setText("Pronto!"); // Atualiza status
                });
            }).start(); // Inicia a thread
        };

        // Ação para o botão "Entrada Manual"
        manualButton.addActionListener(e -> {
            // Exibe um diálogo para o usuário digitar a quantidade de números
            String input = JOptionPane.showInputDialog(this, "Quantos números da sequência você quer ver?");
            if (input != null && !input.isEmpty()) {
                try {
                    int n = Integer.parseInt(input); // Converte para inteiro
                    if (n > 10000) { // Limita a quantidade máxima
                        JOptionPane.showMessageDialog(this, "Número muito grande! Limite: 10.000");
                        return;
                    }
                    gerarComLoading.accept(n); // Gera a sequência com o valor inserido
                } catch (NumberFormatException ex) {
                    // Caso o usuário digite algo inválido
                    JOptionPane.showMessageDialog(this, "Digite um número válido.");
                }
            }
        });

        // Ação para o botão "Automático"
        autoButton.addActionListener(e -> gerarComLoading.accept(15)); // Gera 15 números automaticamente

        setVisible(true); // Exibe a janela
    }

    // Método que gera a sequência de Fibonacci e retorna como string
    private String gerarFibonacci(int n) {
        if (n <= 0) return "Número inválido."; // Verifica se o valor é válido

        StringBuilder resultado = new StringBuilder(); // StringBuilder para montar o texto
        BigInteger a = BigInteger.ZERO; // Primeiro número da sequência
        BigInteger b = BigInteger.ONE;  // Segundo número

        resultado.append(a).append("\n"); // Adiciona o primeiro número à saída

        // Gera os próximos termos da sequência
        for (int i = 1; i < n; i++) {
            BigInteger next = a.add(b); // Soma os dois anteriores
            resultado.append(next).append("\n"); // Adiciona à string de resultado
            a = b; // Atualiza a sequência
            b = next;
        }

        return resultado.toString(); // Retorna a sequência completa em forma de texto
    }
}
