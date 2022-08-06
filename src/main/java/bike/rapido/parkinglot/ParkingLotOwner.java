package bike.rapido.parkinglot;

public class ParkingLotOwner implements Observer {
    private String signBoard;

    public String getSignBoard() {
        return signBoard;
    }

    public void setSignBoard(String signBoard) {
        this.signBoard = signBoard;
    }

    public void putFullSignBoard() {
        setSignBoard("FULL");
    }

    @Override
    public void notifyObserverWhenLotIsFull() {
        putFullSignBoard();
    }

    private void removeFullSignBoard() {
        setSignBoard(null);
    }

    @Override
    public void notifyObserverWhenLotHasSpaceAgain() {
        removeFullSignBoard();
    }
}