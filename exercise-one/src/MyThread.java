import java.util.Random;

public class MyThread implements Runnable {
    private int[] randomNumbers;
    private int startIndex;
    private static final int MAX_RANDOM_NUMBER = 2000;

    public MyThread(int[] randomNumbers, int startIndex) {
        this.randomNumbers = randomNumbers;
        this.startIndex = startIndex;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int randomNumber = random.nextInt(MAX_RANDOM_NUMBER + 1);
            randomNumbers[startIndex + i] = randomNumber;
        }
    }
}
