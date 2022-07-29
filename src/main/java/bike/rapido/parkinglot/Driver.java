package bike.rapido.parkinglot;

class ParkingSpace {
    private static int availableSlots;

    public ParkingSpace(int totalSlots) {
        availableSlots = totalSlots;
    }

    public static  void decrementSlots(){
        availableSlots -= 1;
    }
    public static boolean isAvailable() {
        if(availableSlots > 0){
            return true;
        }else{
            return false;
        }
    }
}

public class Driver {
    public boolean checkAvailabilityAndPark() {
        if(ParkingSpace.isAvailable()) {
            ParkingSpace.decrementSlots();
            return true;
        } else {
            return false;
        }
    }
}