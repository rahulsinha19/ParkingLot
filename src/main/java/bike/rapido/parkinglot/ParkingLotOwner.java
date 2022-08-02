package bike.rapido.parkinglot;

public class ParkingLotOwner implements Observer{
    String signBoard;
    private boolean hasOwnerNotified = false;
    public ParkingLotOwner() {
        register();
    }

    public boolean isOwnerNotified() {
        return hasOwnerNotified;
    }

    public void putFullSignBoard() {
        this.hasOwnerNotified = true;
        this.signBoard = "FULL";
    }

    @Override
    public void register() {
        ParkingSpace.addObserver(this);
    }

    @Override
    public void notifyObserver() {
        putFullSignBoard();
    }
}
