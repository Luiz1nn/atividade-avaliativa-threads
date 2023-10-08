public class VectorSum implements Runnable {
    private int[] vectorOne, vectorTwo, vectorResult;
    private int initialIndex, finalIndex;

    public VectorSum(int[] vectorOne, int[] vectorTwo, int[] vectorResult, int initialIndex, int finalIndex) {
        this.vectorOne = vectorOne;
        this.vectorTwo = vectorTwo;
        this.vectorResult = vectorResult;
        this.initialIndex = initialIndex;
        this.finalIndex = finalIndex;
    }

    @Override
    public void run() {
        for (int i = initialIndex; i < finalIndex; i++) {
            vectorResult[i] = vectorOne[i] + vectorTwo[i];
        }
    }
}
