package uni.knu.vlad.Tasktwo.soldier;

import uni.knu.vlad.Tasktwo.Good;
import uni.knu.vlad.Tasktwo.Storage;

public class Necheporuk implements Runnable {
    private Storage van;
    public Necheporuk(Storage van) {
        this.van = van;
    }

    @Override
    public void run() {
        while (!van.isFinished() || !van.isEmpty()) {
            Good good = van.get();
        }
    }
}