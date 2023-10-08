## Diferenças entre o meu codigo e o gerado no ChatGPT

1. Uso de Threads vs. ExecutorService:
- Na primeira implementação, usamos a classe Thread para criar e gerenciar threads individualmente. Cada elemento da matriz é associado a uma thread separada.
- Na segunda implementação, utilizamos a classe `ExecutorService` em conjunto com um pool de threads. As tarefas são criadas como instâncias da classe TransposeMatrixTask e, em seguida, submetidas ao ExecutorService para execução.

2. Gerenciamento de Threads:
- Na primeira implementação, é criado e iniciado manualmente todas as threads em um loop for.
- Na segunda implementação, o `ExecutorService` gerencia o pool de threads e a execução das tarefas, tornando o código mais simples e conciso.

3. Flexibilidade:
- A primeira implementação permite um controle mais granular sobre as threads individuais, mas pode ser mais complexa de gerenciar.
- A segunda implementação fornece uma abstração mais simples e pronta para uso ao usar o ExecutorService, o que é útil em muitos cenários, especialmente quando se lida com tarefas concorrentes.

      import java.util.Scanner;
      import java.util.concurrent.ExecutorService;
      import java.util.concurrent.Executors;
      import java.util.concurrent.TimeUnit;

      class TransposeMatrixTask implements Runnable {
        private int[][] matrix;
        private int[][] transposedMatrix;
        private int row;
        private int col;

        public TransposeMatrixTask(int[][] matrix, int[][] transposedMatrix, int row, int col) {
          this.matrix = matrix;
          this.transposedMatrix = transposedMatrix;
          this.row = row;
          this.col = col;
        }

        @Override
        public void run() {
          transposedMatrix[col][row] = matrix[row][col];
        }
      }

      public class MatrixTransposeWithThreadPool {
        public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);

          System.out.print("Digite o número de linhas da matriz: ");
          int numRows = scanner.nextInt();

          System.out.print("Digite o número de colunas da matriz: ");
          int numCols = scanner.nextInt();

          int[][] matrix = new int[numRows][numCols];
          int[][] transposedMatrix = new int[numCols][numRows];

          System.out.println("Digite os valores da matriz:");

          for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
              System.out.print("Digite o valor para a posição (" + (i + 1) + ", " + (j + 1) + "): ");
              matrix[i][j] = scanner.nextInt();
            }
          }

          ExecutorService executorService = Executors.newCachedThreadPool();

          for (int i = 0; i < numRows; i++) {
              for (int j = 0; j < numCols; j++) {
                  executorService.execute(new TransposeMatrixTask(matrix, transposedMatrix, i, j));
              }
          }

          executorService.shutdown();

          try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          System.out.println("Matriz original:");
          printMatrix(matrix);

          System.out.println("Matriz transposta:");
          printMatrix(transposedMatrix);

          scanner.close();
        }

        private static void printMatrix(int[][] matrix) {
          for (int[] row : matrix) {
            for (int value : row) {
              System.out.print(value + " ");
            }
            System.out.println();
          }
        }
      }
