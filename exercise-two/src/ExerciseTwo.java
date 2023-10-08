import java.util.Scanner;

public class ExerciseTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o tamanho dos vetores: ");
        int vectorSize = scanner.nextInt();

        int[] vectorOne = new int[vectorSize];
        int[] vectorTwo = new int[vectorSize];

        System.out.println("Digite os elementos do primeiro vetor:");
        for (int i = 0; i < vectorSize; i++) {
            vectorOne[i] = scanner.nextInt();
        }

        System.out.println("Digite os elementos do segundo vetor:");
        for (int i = 0; i < vectorSize; i++) {
            vectorTwo[i] = scanner.nextInt();
        }

        int[] vectorResult = new int[vectorSize];

        Thread threadOne = new Thread(new VectorSum(vectorOne, vectorTwo, vectorResult, 0, vectorSize / 2));
        Thread threadTwo = new Thread(new VectorSum(vectorOne, vectorTwo, vectorResult, vectorSize / 2, vectorSize));

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mergeSort(vectorResult, 0, vectorSize - 1);

        System.out.println("Resultado dos Vetores (ordenado em ordem decrescente):");
        for (int i = 0; i < vectorSize; i++) {
            System.out.print(vectorResult[i] + " ");
        }
    }

    public static void mergeSort(int[] vector, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;

            mergeSort(vector, start, middle);
            mergeSort(vector, middle + 1, end);

            merge(vector, start, middle, end);
        }
    }

    public static void merge(int[] vector, int start, int middle, int end) {
        int n1 = middle - start + 1;
        int n2 = end - middle;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = vector[start + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = vector[middle + 1 + j];
        }

        int i = 0, j = 0;
        int k = start;

        while (i < n1 && j < n2) {
            if (leftArray[i] >= rightArray[j]) {
                vector[k] = leftArray[i];
                i++;
            } else {
                vector[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            vector[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            vector[k] = rightArray[j];
            j++;
            k++;
        }
    }
}