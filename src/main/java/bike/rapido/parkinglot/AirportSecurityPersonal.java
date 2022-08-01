package bike.rapido.parkinglot;

public class AirportSecurityPersonal {
    private final ParkingSpace parkingSpace;
    public AirportSecurityPersonal(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }


    public boolean redirectSecurityStaff() {
        if(parkingSpace.isFull()) {
            return true;
        } else {
            return false;
        }
    }
}
