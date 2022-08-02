package bike.rapido.parkinglot;

import java.util.ArrayList;

class ParkingSpace {
    private static int availableSlots;
    private static int totalSlots;

    private static ArrayList<Observer> registeredObserversForNotifyingLotIsFull;
    private static ArrayList<Observer> registeredObserversForNotifyingLotHasSpaceAgain;


    public static void registerForNotifyingLotIsFull(Observer observer){
        registeredObserversForNotifyingLotIsFull.add(observer);
    }
    public static void registerForNotifyingWhenLotHasSpaceAgain(ParkingLotOwner parkingLotOwner) {
        registeredObserversForNotifyingLotHasSpaceAgain.add(parkingLotOwner);
    }
    public ParkingSpace(int totalSlots) {
        availableSlots = totalSlots;
        ParkingSpace.totalSlots = totalSlots;

        registeredObserversForNotifyingLotIsFull = new ArrayList<>();
        registeredObserversForNotifyingLotHasSpaceAgain = new ArrayList<>();
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
            notifyObserversWhenLotIsFull();
        }
    }

    static void notifyObserversWhenLotIsFull() {
        for (Observer observer: registeredObserversForNotifyingLotIsFull){
            observer.notifyObserverWhenLotIsFull();
        }
    }

    static void notifyObserversWhenLotHasSpaceAgain(){
        System.out.println(registeredObserversForNotifyingLotHasSpaceAgain.get(0));
        for(Observer observer: registeredObserversForNotifyingLotHasSpaceAgain){

            observer.notifyObserverWhenLotHasSpaceAgain();
        }
    }

    public static void unParkACar() {
        if(isFull()) {

            incrementSlots();
            notifyObserversWhenLotHasSpaceAgain();
        }else {
            incrementSlots();
        }
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