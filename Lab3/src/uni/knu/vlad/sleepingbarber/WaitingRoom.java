package uni.knu.vlad.sleepingbarber;


import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;

public class WaitingRoom {
    private int freeChairs;
    private Semaphore accessChair;
    private Semaphore waitingRoom;
    private BarberRoom barber;
    private ArrayDeque<String> visitors;

    public WaitingRoom(int chairsNum) throws InterruptedException {
        System.out.println("There are " + chairsNum + " chairs in the barbershop");
        this.freeChairs = chairsNum;
        this.accessChair = new Semaphore(1, true);

        this.waitingRoom = new Semaphore(chairsNum, true);
        for(int i = 0; i < chairsNum; i++) {
            waitingRoom.acquire();
        }
        this.visitors = new ArrayDeque<>(chairsNum);
    }

    public void setBarberRoom(BarberRoom barber) {
        this.barber = barber;
    }

    public void  getIn() throws InterruptedException {
        accessChair.acquire();
        if(freeChairs == 0) {
            System.out.println("There are no free seats. Client " + Thread.currentThread().getName() +
                    " has left");
            accessChair.release();
        } else {
            takeSit();
            System.out.println("Client " + Thread.currentThread().getName() +
                    " has taken a chair");
            visitors.add(Thread.currentThread().getName());
            accessChair.release();
            waitingRoom.release();
            barber.acquire();
            Thread.currentThread().interrupt();
        }
    }
    public void takeSit() {
        freeChairs--;
    }
    public void releaseSeat() {
        freeChairs++;
    }
    public String getVisitor() {
        return visitors.poll();
    }
    public void acquire() throws InterruptedException {
        waitingRoom.acquire();
    }
    private void release() {
        waitingRoom.release();
    }
}