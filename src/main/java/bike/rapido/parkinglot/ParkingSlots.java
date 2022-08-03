package bike.rapido.parkinglot;

import java.util.ArrayList;

class ParkingSlots {
	private int availableSlots;
	private int totalSlots;

	private static ArrayList<Observer> registeredObserversForNotifyingLotIsFull;
	private static ArrayList<Observer> registeredObserversForNotifyingLotHasSpaceAgain;

	public static void registerForNotifyingLotIsFull(Observer observer) {
		registeredObserversForNotifyingLotIsFull.add(observer);
	}

	public static void registerForNotifyingWhenLotHasSpaceAgain(ParkingLotOwner parkingLotOwner) {
		registeredObserversForNotifyingLotHasSpaceAgain.add(parkingLotOwner);
	}

	public ParkingSlots(int totalSlots) {
		this.availableSlots = totalSlots;
		this.totalSlots = totalSlots;

		registeredObserversForNotifyingLotIsFull = new ArrayList<>();
		registeredObserversForNotifyingLotHasSpaceAgain = new ArrayList<>();
	}

	private void decrementSlots() {
		this.availableSlots -= 1;
	}

	private void incrementSlots() {
		this.availableSlots += 1;
	}

	public boolean isAvailable() {
		if (this.availableSlots > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmpty() {
		if (this.availableSlots == totalSlots) {
			return true;
		} else {
			return false;
		}
	}

	public void parkACar() {
		decrementSlots();
		if (isFull()) {
			notifyObserversWhenLotIsFull();
		}
	}

	static void notifyObserversWhenLotIsFull() {
		for (Observer observer : registeredObserversForNotifyingLotIsFull) {
			observer.notifyObserverWhenLotIsFull();
		}
	}

	static void notifyObserversWhenLotHasSpaceAgain() {
		System.out.println(registeredObserversForNotifyingLotHasSpaceAgain.get(0));
		for (Observer observer : registeredObserversForNotifyingLotHasSpaceAgain) {

			observer.notifyObserverWhenLotHasSpaceAgain();
		}
	}

	public void unParkACar() {
		if (isFull()) {

			incrementSlots();
			notifyObserversWhenLotHasSpaceAgain();
		} else {
			incrementSlots();
		}
	}

	public boolean isFull() {
		return this.availableSlots == 0;
	}

}
