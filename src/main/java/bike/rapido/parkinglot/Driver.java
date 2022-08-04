package bike.rapido.parkinglot;

public class Driver {
    private ParkingSlots parkingSlots;

    Driver(final int totalSlots) {
        this.parkingSlots = new ParkingSlots(totalSlots);
    }

    public boolean parkVehicle(Car car) {
        if (parkingSlots.isAvailable()) {
            parkingSlots.parkACar(car);
            return true;
        } else {
            return false;
        }
    }

    public boolean unParkVehicle(Car car) {
        if (parkingSlots.isFullyEmpty()) {
            return false;
        } else {
            parkingSlots.unParkACar(car);
            return true;
        }
    }

    public ParkingSlots getParkingSlot() {
        return this.parkingSlots;
    }
}
