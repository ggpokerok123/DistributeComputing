package uni.knu.vlad.sleepingbarber;

public class Client implements Runnable {
    WaitingRoom waitingRoom;
    public Client(WaitingRoom waitingRoom) {
        this.waitingRoom = waitingRoom;
    }

    @Override
    public void run() {
        try {
            waitingRoom.getIn();
        } catch (InterruptedException e) {
            System.out.println("Client " + Thread.currentThread().getName() +
                    "was thrown away from the barbershop");
            Thread.currentThread().interrupt();
        }
    }
}
