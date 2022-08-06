package bike.rapido.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ParkingSlots {
    private final int totalSlots;
    private final ArrayList<Observer> registeredObserversForNotifyingLotIsFull;
    private final ArrayList<Observer> registeredObserversForNotifyingLotHasSpaceAgain;
    private final HashMap<Integer, Car> carParkedSlotDetails;
    private final HashMap<Car, Integer> carUnParkedDLotsDetails;
    private int availableSlots;

    public ParkingSlots(int totalSlots) {
        this.availableSlots = totalSlots;
        this.totalSlots = totalSlots;

        registeredObserversForNotifyingLotIsFull = new ArrayList<>();
        registeredObserversForNotifyingLotHasSpaceAgain = new ArrayList<>();

        this.carParkedSlotDetails = new HashMap<>();
        for (int count = 0; count < totalSlots; count++) {
            carParkedSlotDetails.put(count, null);
        }
        this.carUnParkedDLotsDetails = new HashMap<>();
    }

    public void registerForNotifyingLotIsFull(Observer observer) {
        registeredObserversForNotifyingLotIsFull.add(observer);
    }

    public void registerForNotifyingWhenLotHasSpaceAgain(ParkingLotOwner parkingLotOwner) {
        registeredObserversForNotifyingLotHasSpaceAgain.add(parkingLotOwner);
    }

    private void decrementSlots() {
        this.availableSlots -= 1;
    }

    private void incrementSlots() {
        this.availableSlots += 1;
    }

    public boolean isAvailable() {
        return this.availableSlots > 0;
    }

    public boolean isFullyEmpty() {
        return this.availableSlots == totalSlots;
    }

    public void notifyObserversWhenLotIsFull() {
        for (Observer observer : registeredObserversForNotifyingLotIsFull) {
            observer.notifyObserverWhenLotIsFull();
        }
    }

    public void notifyObserversWhenLotHasSpaceAgain() {
        for (Observer observer : registeredObserversForNotifyingLotHasSpaceAgain) {

            observer.notifyObserverWhenLotHasSpaceAgain();
        }
    }

    public boolean isFull() {
        return this.availableSlots == 0;
    }

    public boolean parkACar(Car car) {
        if (isFull()) {
            return false;
        }

        int availableSlot = getEmptySlotFromMap();
        if (availableSlot == -1) {
            return false;
        }

        carParkedSlotDetails.put(availableSlot, car);
        carUnParkedDLotsDetails.put(car, availableSlot);
        decrementSlots();
        if (isFull()) {
            notifyObserversWhenLotIsFull();
        }
        return true;
    }

    public boolean getCar(Car car) {
        return carUnParkedDLotsDetails.containsKey(car);
    }

    public boolean unParkACar(Car car) {
        if (isFullyEmpty()) {
            return false;
        }
        getSlotIdAndRemoveCarFromSlot(car);

        if (isFull()) {
            notifyObserversWhenLotHasSpaceAgain();
        }

        incrementSlots();
        return true;
    }

    private void getSlotIdAndRemoveCarFromSlot(Car car) {
        int carParkedSlotId = getSlotIdWhereCarIsParked(car);
        carUnParkedDLotsDetails.remove(car);
        carParkedSlotDetails.put(carParkedSlotId, null);
    }

    private int getEmptySlotFromMap() {
        for (Map.Entry<Integer, Car> entry : carParkedSlotDetails.entrySet()) {
            if (entry.getValue() == null) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public int getSlotIdWhereCarIsParked(Car car) {
        return carUnParkedDLotsDetails.get(car);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getEmptySlots() {
        return this.availableSlots;
    }
}