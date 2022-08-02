package bike.rapido.parkinglot;

import java.util.ArrayList;

class ParkingSpace {
    private static int availableSlots;
    private static int totalSlots;

    private static ArrayList<Observer> registeredObservers;

    public static void  addObserver(Observer observer){
        registeredObservers.add(observer);
    }

    public ParkingSpace(int totalSlots) {
        registeredObservers = new ArrayList<>();
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
        if(isFull()){
            notifyObservers();
        }
    }

    static void notifyObservers() {
        for (Observer ob: registeredObservers){
            ob.notifyObserver();
        }
    }

    public static void unParkACar() {
        incrementSlots();
    }

    public static  boolean isFull() {
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