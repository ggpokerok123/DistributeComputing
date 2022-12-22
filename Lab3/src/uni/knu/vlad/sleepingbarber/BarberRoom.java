package uni.knu.vlad.sleepingbarber;

import java.util.concurrent.Semaphore;

public class BarberRoom {
    private Semaphore barber;
    private WaitingRoom room;

    public BarberRoom() throws InterruptedException {
        barber = new Semaphore(1, true);
    }
    public void setWaitingRoom(WaitingRoom room) {
        this.room = room;
    }
    public void makeHairCut() throws InterruptedException {
        while (true) {
            room.acquire();
            room.releaseSeat();
            String visitor = room.getVisitor();
            System.out.println("Barber has invited client " + visitor);
            Thread.sleep(1000);
            System.out.println("Barber has just served client " + visitor);
            barber.release();
        }
    }

    public void acquire() throws InterruptedException {
        barber.acquire();
    }

    public void release() {
        barber.release();
    }
}