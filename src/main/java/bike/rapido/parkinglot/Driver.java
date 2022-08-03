package bike.rapido.parkinglot;

public class Driver {
	private ParkingSlots parkingSlots;

	Driver(final int totalSlots) {
		this.parkingSlots = new ParkingSlots(totalSlots);
	}

	public boolean parkVehicle() {
		if (parkingSlots.isAvailable()) {
			parkingSlots.parkACar();
			return true;
		} else {
			return false;
		}
	}

	public boolean unParkVehicle() {
		if (parkingSlots.isEmpty()) {
			return false;
		} else {
			parkingSlots.unParkACar();
			return true;
		}
	}
}
