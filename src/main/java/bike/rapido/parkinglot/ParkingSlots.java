package bike.rapido.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ParkingSlots {
	private int availableSlots;
	private final int totalSlots;

	private final ArrayList<Observer> registeredObserversForNotifyingLotIsFull;
	private final ArrayList<Observer> registeredObserversForNotifyingLotHasSpaceAgain;

	private final HashMap<Integer, Car> carParkedDetails;
	private final HashMap<Car, Integer> carParkedDetails2;

	public void registerForNotifyingLotIsFull(Observer observer) {
		registeredObserversForNotifyingLotIsFull.add(observer);
	}

	public void registerForNotifyingWhenLotHasSpaceAgain(ParkingLotOwner parkingLotOwner) {
		registeredObserversForNotifyingLotHasSpaceAgain.add(parkingLotOwner);
	}

	public ParkingSlots(int totalSlots) {
		this.availableSlots = totalSlots;
		this.totalSlots = totalSlots;

		registeredObserversForNotifyingLotIsFull = new ArrayList<>();
		registeredObserversForNotifyingLotHasSpaceAgain = new ArrayList<>();

		this.carParkedDetails = new HashMap<Integer, Car>();
		for (int count = 0; count < totalSlots; count++) {
			carParkedDetails.put(count, null);
		}
		this.carParkedDetails2 = new HashMap<Car, Integer>();
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

		carParkedDetails.put(availableSlot, car);
		carParkedDetails2.put(car, availableSlot);
		decrementSlots();
		if (isFull()) {
			notifyObserversWhenLotIsFull();
		}
		return true;
	}

	public boolean getCar(Car car) {
		return carParkedDetails2.containsKey(car) ;
	}

	public boolean unParkACar(Car car) {
		if (isFullyEmpty()) {
			return false;
		}
		int carParkedSlotId = carParkedDetails2.get(car);
		System.out.println("================================== : " + carParkedSlotId);
		carParkedDetails2.remove(car);
		carParkedDetails.put(carParkedSlotId, null);
		if (isFull()) {
			notifyObserversWhenLotHasSpaceAgain();
		}

		incrementSlots();
		return true;
	}

	private int getEmptySlotFromMap() {
		for (Map.Entry<Integer, Car> entry : carParkedDetails.entrySet()) {
			if (entry.getValue() == null) {
				return entry.getKey();
			}
		}
		return -1;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
