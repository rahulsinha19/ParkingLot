package bike.rapido.parkinglot;

class ParkingSpace {
    private static int availableSlots;

    public ParkingSpace(int totalSlots) {
        availableSlots = totalSlots;
    }

    private static  void decrementSlots(){
        availableSlots -= 1;
    }

    public static void parkACar(){
        decrementSlots();
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
            ParkingSpace.parkACar();
            return true;
        } else {
            return false;
        }
    }
}