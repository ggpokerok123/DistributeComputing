package uni.knu.vlad.Tasktwo.soldier;

import uni.knu.vlad.Tasktwo.Good;
import uni.knu.vlad.Tasktwo.Storage;


public class Petrov implements Runnable {
    private Storage outdoor;
    private Storage van;

    public Petrov(Storage outdoor, Storage van) {
        this.outdoor = outdoor;
        this.van = van;
    }

    @Override
    public void run() {
        while (!outdoor.isFinished() || !outdoor.isEmpty()) {
            Good good = outdoor.get();
            van.put(good);
        }
        van.setFinish();
    }
}