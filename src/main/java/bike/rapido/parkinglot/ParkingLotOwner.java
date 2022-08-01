package bike.rapido.parkinglot;

public class ParkingLotOwner {
    private final ParkingSpace parkingSpace;

    public ParkingLotOwner(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public boolean checkIfParkingLotIsFull() {
        return parkingSpace.isFull();
    }

    public String putSignBoard() {
        if(checkIfParkingLotIsFull()){
            return "FULL";
        }else{
            return "NOT FULL";
        }
    }
}
