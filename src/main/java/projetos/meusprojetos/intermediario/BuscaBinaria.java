package projetos.meusprojetos.intermediario;

public class BuscaBinaria {

    public static int buscaBinaria(int[] array, int valor) {
        int inicio = 0;
        int fim = array.length - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (array[meio] == valor) {
                return meio; // encontrou, retorna a posição
            } else if (array[meio] < valor) {
                inicio = meio + 1; // procura na metade direita
            } else {
                fim = meio - 1; // procura na metade esquerda
            }
        }

        return -1; // não encontrou
    }

    public static void main(String[] args) {
        int[] numeros = {1, 3, 5, 7, 9, 11, 13};
        int valor = 7;

        int resultado = buscaBinaria(numeros, valor);
        if (resultado != -1) {
            System.out.println("Valor encontrado na posição: " + resultado);
        } else {
            System.out.println("Valor não encontrado.");
        }
    }

}
