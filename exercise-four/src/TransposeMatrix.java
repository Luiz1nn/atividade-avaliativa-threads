public class TransposeMatrix extends Thread {
    private int[][] matrix, transposedMatrix;
    private int row, col;

    public TransposeMatrix(int[][] matrix, int[][] transposedMatrix, int row, int col) {
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
