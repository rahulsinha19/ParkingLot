package bike.rapido.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingAttendant {
    List<ParkingSlots> parkingSlots;

    ParkingAttendant(int totalLots, int totalSlots) {
        this.parkingSlots = new ArrayList<>();
        for (int count = 0; count < totalLots; count++) {
            parkingSlots.add(new ParkingSlots(totalSlots));
        }
    }

    public boolean parkTheCar(Car car) {
        int lotIdToParkCarIn = getParkingSlotAreaIdWithMinimumCarsParked();
        ParkingSlots parkCarInSlot = parkingSlots.get(lotIdToParkCarIn);
        return parkCarInSlot.parkACar(car);
    }

    public boolean getEmptySlots() {
        for (ParkingSlots ele : parkingSlots) {
            if (ele.isFullyEmpty()) {
                return true;
            }
        }
        return false;
    }

    public boolean unParkTheCar(Car car) {
        for (ParkingSlots ele : this.parkingSlots) {
            if (ele.getCar(car)) {
                return ele.unParkACar(car);
            }
        }
        return false;
    }

    public int[] getSlotDetailsWhereCarIsParked(Car car) {

        for (int count = 0; count < parkingSlots.size(); count++) {
            if (parkingSlots.get(count).getCar(car)) {

                int[] parkingSlotDetails = new int[2];
                int slotId = parkingSlots.get(count).getSlotIdWhereCarIsParked(car);

                parkingSlotDetails[0] = count;
                parkingSlotDetails[1] = slotId;

                return parkingSlotDetails;
            }
        }
        return new int[2];
    }

    private int getParkingSlotAreaIdWithMinimumCarsParked() {
        int parkingSlotId = 0;
        int carsParkedInASlot = Integer.MIN_VALUE;

        for (int count = 0; count < parkingSlots.size(); ++count) {
            int carsParkedInCurrentSlot = parkingSlots.get(count).getEmptySlots();

            if (carsParkedInASlot < carsParkedInCurrentSlot) {
                carsParkedInASlot = carsParkedInCurrentSlot;
                parkingSlotId = count;
            }
        }
        return parkingSlotId;
    }

}