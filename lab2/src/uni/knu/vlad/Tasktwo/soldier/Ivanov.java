package uni.knu.vlad.Tasktwo.soldier;


import uni.knu.vlad.Tasktwo.Good;
import uni.knu.vlad.Tasktwo.Storage;

import java.util.ArrayList;

public class Ivanov implements Runnable {
    private ArrayList<Good> warehouse;
    private Storage outer;

    public Ivanov(ArrayList<Good> warehouse, Storage outer) {
        this.warehouse = warehouse;
        this.outer = outer;
    }

    @Override
    public void run() {
        while (warehouse.size() > 0) {
            Good good = warehouse.get(warehouse.size()-1);
            warehouse.remove(warehouse.size()-1);
            outer.put(good);
        }
        outer.setFinish();
    }
}