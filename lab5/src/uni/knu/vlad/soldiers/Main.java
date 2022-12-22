package uni.knu.vlad.soldiers;

public class Main {
    public static void main(String[] args) {
        final int threadNumber = 5;
        final Formation formation = new Formation(500, threadNumber);
        for(int i = 0; i < threadNumber; ++i) {
            new Thread(new Worker(formation, i * 100, (i + 1) * 100 - 1)).start();
        }
    }
}