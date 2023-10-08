import java.util.Scanner;

public class ExerciseFour {
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

        TransposeMatrix[][] threads = new TransposeMatrix[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                threads[i][j] = new TransposeMatrix(matrix, transposedMatrix, i, j);
                threads[i][j].start();
            }
        }

        try {
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    threads[i][j].join();
                }
            }
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

