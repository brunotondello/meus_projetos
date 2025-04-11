package projetos.meusprojetos.avancado.interpretadordeexpressoes;

import java.util.*;

public class UtilExpressao {

    public static double avaliar(String expressao) {
        List<String> posfixa = converterParaPosfixa(expressao);
        return avaliarPosfixa(posfixa);
    }

    private static List<String> converterParaPosfixa(String expressao) {
        Stack<String> operadores = new Stack<>();
        List<String> saida = new ArrayList<>();

        StringTokenizer tokens = new StringTokenizer(expressao, "+-*/() ", true);

        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (token.isEmpty()) continue;

            if (token.matches("\\d+(\\.\\d+)?")) {
                saida.add(token);
            } else if ("(".equals(token)) {
                operadores.push(token);
            } else if (")".equals(token)) {
                while (!operadores.isEmpty() && !operadores.peek().equals("(")) {
                    saida.add(operadores.pop());
                }
                operadores.pop(); // remove "("
            } else if (isOperador(token)) {
                while (!operadores.isEmpty() && precedencia(token) <= precedencia(operadores.peek())) {
                    saida.add(operadores.pop());
                }
                operadores.push(token);
            }
        }

        while (!operadores.isEmpty()) {
            saida.add(operadores.pop());
        }

        return saida;
    }

    private static double avaliarPosfixa(List<String> posfixa) {
        Stack<Double> pilha = new Stack<>();

        for (String token : posfixa) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                pilha.push(Double.parseDouble(token));
            } else {
                double b = pilha.pop();
                double a = pilha.pop();
                switch (token) {
                    case "+" -> pilha.push(a + b);
                    case "-" -> pilha.push(a - b);
                    case "*" -> pilha.push(a * b);
                    case "/" -> pilha.push(a / b);
                }
            }
        }

        return pilha.pop();
    }

    private static boolean isOperador(String token) {
        return "+-*/".contains(token);
    }

    private static int precedencia(String op) {
        return (op.equals("+") || op.equals("-")) ? 1 : 2;
    }
}
