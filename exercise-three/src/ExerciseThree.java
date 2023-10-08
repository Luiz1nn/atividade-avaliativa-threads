import java.util.Scanner;

public class ExerciseThree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = 3;
        int cols = 3;

        System.out.println("Digite os valores para a primeira matriz (3x3):");
        int[][] matrizOne = readMatriz(scanner, rows, cols);

        System.out.println("Digite os valores para a segunda matriz (3x3):");
        int[][] matrizTwo = readMatriz(scanner, rows, cols);

        int[][] result = new int[rows][cols];
        Thread[] threads = new Thread[rows];

        for (int i = 0; i < rows; i++) {
            threads[i] = new Thread(new RowMultiplication(matrizOne, matrizTwo, result, i));
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Matriz Resultante (Multiplicação):");
        printMatriz(result);
    }

    public static int[][] readMatriz(Scanner scanner, int rows, int cols) {
        int[][] matriz = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Digite o valor para a posição [" + i + "][" + j + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }
        return matriz;
    }

    public static void printMatriz(int[][] matriz) {
        int rows = matriz.length;
        int cols = matriz[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
