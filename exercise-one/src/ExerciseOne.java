import java.util.Arrays;

public class ExerciseOne {
    public static void main(String[] args) {
        int numberThreads = 5;
        int quantityNumbersGenerated = 20;
        int[] randomNumbers = new int[100];
        Thread[] threads = new Thread[numberThreads];

        for (int i = 0; i < numberThreads; i++) {
            threads[i] = new Thread(new MyThread(randomNumbers, i * quantityNumbersGenerated));
            threads[i].start();
        }

        try {
            for (int i = 0; i < 5; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Arrays.sort(randomNumbers);

        System.out.println("Números gerados em ordem crescente:");
        for (Integer number : randomNumbers) {
            System.out.println(number);
        }
    }
}
