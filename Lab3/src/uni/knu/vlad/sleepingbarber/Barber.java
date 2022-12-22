package uni.knu.vlad.sleepingbarber;
public class Barber implements Runnable{
    private BarberRoom barberRoom;
    public Barber(BarberRoom barberRoom) {
        this.barberRoom = barberRoom;
    }
    @Override
    public void run() {
        try {
            barberRoom.makeHairCut();
        } catch (InterruptedException e) {
            System.out.println("Barber finished work");
            Thread.currentThread().interrupt();
        }
    }
}