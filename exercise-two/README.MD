## Diferenças entre o meu codigo e o gerado no ChatGPT

1. Gerenciamento de Threads:
- Na primeira implementação, é criado duas threads `threadOne, threadTwo` e as iniciamos manualmente. Em seguida, usamos o método join para esperar que ambas as threads terminem antes de prosseguir com a ordenação.
- Na segunda implementação, é utilizado um ExecutorService para gerenciar as threads. Isso simplifica o gerenciamento de threads, pois o executor cuida da criação, execução e término das threads. O método awaitTermination é usado para aguardar que todas as threads no executor terminem.

2. Ordenação Merge Sort:
- Na primeira implementação, o algoritmo de ordenação Merge Sort é implementado de forma iterativa usando loops. A função mergeSort divide o vetor em subvetores menores e, em seguida, mescla esses subvetores.
- Na segunda implementação, o algoritmo de ordenação Merge Sort é implementado de forma recursiva. Isso é mais comum na implementação do Merge Sort e é mais legível. A função mergeSort é chamada recursivamente para dividir o vetor em subvetores menores até que cada subvetor tenha apenas um elemento. Em seguida, os subvetores são mesclados recursivamente em um vetor ordenado.

`

    import java.util.Arrays;
    import java.util.Scanner;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;
    import java.util.concurrent.TimeUnit;
    
    public class MergeSortParallel {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
            System.out.print("Digite o tamanho dos vetores: ");
            int tamanho = scanner.nextInt();
    
            int[] vetor1 = new int[tamanho];
            int[] vetor2 = new int[tamanho];
    
            System.out.println("Digite os elementos do primeiro vetor:");
            for (int i = 0; i < tamanho; i++) {
                vetor1[i] = scanner.nextInt();
            }
    
            System.out.println("Digite os elementos do segundo vetor:");
            for (int i = 0; i < tamanho; i++) {
                vetor2[i] = scanner.nextInt();
            }
    
            int[] vetorResultado = new int[tamanho];
    
            ExecutorService executor = Executors.newFixedThreadPool(2);
    
            // Divide o trabalho em duas partes iguais para as duas threads
            int metade = tamanho / 2;
            executor.submit(new SomaVetorRunnable(vetor1, vetor2, vetorResultado, 0, metade));
            executor.submit(new SomaVetorRunnable(vetor1, vetor2, vetorResultado, metade, tamanho));
    
            executor.shutdown();
    
            try {
                executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    
            // Ordenar o vetor resultante usando o Merge Sort
            mergeSort(vetorResultado);
    
            // Imprimir o vetor ordenado em ordem decrescente
            System.out.println("Vetor Resultante (ordenado em ordem decrescente):");
            for (int i = tamanho - 1; i >= 0; i--) {
                System.out.print(vetorResultado[i] + " ");
            }
        }
        
        // Implementação do algoritmo de ordenação Merge Sort
        public static void mergeSort(int[] vetor) {
            if (vetor.length <= 1) {
                return;
            }
    
            int meio = vetor.length / 2;
            int[] esquerda = Arrays.copyOfRange(vetor, 0, meio);
            int[] direita = Arrays.copyOfRange(vetor, meio, vetor.length);
    
            mergeSort(esquerda);
            mergeSort(direita);
    
            merge(vetor, esquerda, direita);
        }
    
        public static void merge(int[] resultado, int[] esquerda, int[] direita) {
            int i = 0, j = 0, k = 0;
            while (i < esquerda.length && j < direita.length) {
                if (esquerda[i] >= direita[j]) {
                    resultado[k++] = esquerda[i++];
                } else {
                    resultado[k++] = direita[j++];
                }
            }
    
            while (i < esquerda.length) {
                resultado[k++] = esquerda[i++];
            }
    
            while (j < direita.length) {
                resultado[k++] = direita[j++];
            }
        }
    }
    
    class SomaVetorRunnable implements Runnable {
        private int[] vetor1;
        private int[] vetor2;
        private int[] vetorResultado;
        private int inicio;
        private int fim;
    
        public SomaVetorRunnable(int[] vetor1, int[] vetor2, int[] vetorResultado, int inicio, int fim) {
            this.vetor1 = vetor1;
            this.vetor2 = vetor2;
            this.vetorResultado = vetorResultado;
            this.inicio = inicio;
            this.fim = fim;
        }
    
        @Override
        public void run() {
            for (int i = inicio; i < fim; i++) {
                vetorResultado[i] = vetor1[i] + vetor2[i];
            }
        }
    }
