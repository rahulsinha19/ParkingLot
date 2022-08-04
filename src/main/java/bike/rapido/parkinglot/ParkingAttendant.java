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
        for (ParkingSlots ele : parkingSlots) {
            if (ele.isAvailable()) {
                return ele.parkACar(car);
            }
        }
        return false;
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
        for (ParkingSlots ele : parkingSlots) {
            if (ele.getCar(car)) {
                ele.unParkACar(car);
                return true;
            }
        }
        return false;
    }

    public int[] getSlotDetailsWhereCarIsParked(Car car) {

        for (int count = 0; count < parkingSlots.size(); count++) {
            if (parkingSlots.get(count).getCar(car)) {
                // return count,
                int[] parkingSlotDetails = new int[2];
                Integer slotId = parkingSlots.get(count).getSlodIdWhereCarIsParked(car);
                parkingSlotDetails[0] = count;
                parkingSlotDetails[1] = slotId;
                return parkingSlotDetails;
            }
        }
        return new int[2];
    }

}
