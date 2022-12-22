package uni.knu.vlad.semaphore;


public class Semaphore {
    private final int permission;
    private final boolean right;
    private int free;

    public Semaphore(int permission) {
        this.permission = permission;
        this.right = false;
        this.free = permission;
    }

    public Semaphore(int permission, boolean right) {
        this.permission = permission;
        this.free = permission;
        this.right = right;
    }

    public synchronized void acquire() throws InterruptedException {
        while (free == 0) wait();
        free--;
    }

    public synchronized void release() {
        if (free < permission) free++;
        if (right) notify();
        else notifyAll();
    }
}
