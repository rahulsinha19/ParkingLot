package bike.rapido.parkinglot;

class ParkingSpace {
    private static int availableSlots;
    private static int totalSlots;

    public ParkingSpace(int totalSlots) {
        availableSlots = totalSlots;
        ParkingSpace.totalSlots = totalSlots;

    }

    private static  void decrementSlots(){
        availableSlots -= 1;
    }
    private static void incrementSlots() {
        availableSlots += 1;
    }


    public static boolean isAvailable() {
        if(availableSlots > 0){
            return true;
        }else{
            return false;
        }
    }
    public static boolean isEmpty() {
        if(availableSlots == totalSlots){
            return true;
        }else{
            return false;
        }
    }
    public static void parkACar(){
        decrementSlots();
    }
    public static void unParkACar() {
        incrementSlots();
    }

    public boolean isFull() {
        return availableSlots == 0;
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

    public boolean unParkTheCar() {
        if(ParkingSpace.isEmpty()){
            return false;
        }else {
            ParkingSpace.unParkACar();
            return true;
        }
    }
}