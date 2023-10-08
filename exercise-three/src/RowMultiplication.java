public class RowMultiplication implements Runnable {
    private int[][] matrizOne, matrizTwo, result;
    private int row;

    public RowMultiplication(int[][] matrizOne, int[][] matrizTwo, int[][] result, int row) {
        this.matrizOne = matrizOne;
        this.matrizTwo = matrizTwo;
        this.result = result;
        this.row = row;
    }

    @Override
    public void run() {
        int cols = matrizTwo[0].length;

        for (int j = 0; j < cols; j++) {
            for (int k = 0; k < cols; k++) {
                result[row][j] += matrizOne[row][k] * matrizTwo[k][j];
            }
        }
    }
}
