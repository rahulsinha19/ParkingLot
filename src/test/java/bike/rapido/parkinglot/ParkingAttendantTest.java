package bike.rapido.parkinglot;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class ParkingAttendantTest {

    ParkingAttendant parkingAttendant;

    @Before
    public void setUp() {
        int TOTAL_SLOTS = 10;
        int TOTAL_LOTS = 5;
        parkingAttendant = new ParkingAttendant(TOTAL_LOTS, TOTAL_SLOTS);
    }

    @Test
    public void shouldFindTheEmptySlot() {
        boolean isEmptySlotPreset = parkingAttendant.getEmptySlots();

        assertTrue(isEmptySlotPreset);
    }

    @Test
    public void shouldReturnFalseIfNoEmptySlotPresent() {
        ParkingAttendant parkingAttendant = new ParkingAttendant(1, 1);
        Car car = new Car("TestCar");

        boolean isParked = parkingAttendant.parkTheCar(car);
        boolean isUnParked = parkingAttendant.unParkTheCar(car);
        boolean isEmptySlotPreset = parkingAttendant.getEmptySlots();

        assertTrue(isParked);
        assertTrue(isUnParked);
        assertTrue(isEmptySlotPreset);
    }

    @Test
    public void shouldReturnEmptySlotPresentAfterParkAndUnparkACar() {

        ParkingAttendant parkingAttendant = new ParkingAttendant(1, 1);
        Car car = new Car("TestCar");

        boolean isParked = parkingAttendant.parkTheCar(car);
        boolean isUnParked = parkingAttendant.unParkTheCar(car);
        boolean isEmptySlotPreset = parkingAttendant.getEmptySlots();

        assertTrue(isParked);
        assertTrue(isUnParked);
        assertTrue(isEmptySlotPreset);
    }

    @Test
    public void shouldParkTheCar() {
        Car car = new Car("Test Car 1234");

        boolean isCarParked = parkingAttendant.parkTheCar(car);

        assertTrue(isCarParked);
    }

    @Test
    public void shouldUnParkTheCar() {
        Car car = new Car("TestCar Bugatti");

        boolean isCarParked = parkingAttendant.parkTheCar(car);
        boolean isCarUnParked = parkingAttendant.unParkTheCar(car);

        assertTrue(isCarParked);
        assertTrue(isCarUnParked);
    }

//     @Test
//     public void attendantShouldNotBeAbleToParkTheCarWithNoSlotsAndNoLots() {
//     ParkingAttendant parkingAttendant = new ParkingAttendant(0, 0);
//     Car car = new Car("Testing mercedes");
//
//     boolean isCarParked = parkingAttendant.parkTheCar(car);
//
//     assertFalse(isCarParked);
//     }

    @Test
    public void shouldReturnTheSlotWhereACarIsParked() {
        int carsToBeParked = 3;
        int totalSlots = 3, totalLots = 2;
        ParkingAttendant parkingAttendant = new ParkingAttendant(totalLots,
            totalSlots);

        for (int carsParked = 0; carsParked < carsToBeParked; carsParked++) {
            String currentCar = UUID.randomUUID().toString();
            parkingAttendant.parkTheCar(new Car(currentCar));
        }

        Car car = new Car("DL5CQ 0258");

        int[] expectedCarParkedAt = new int[]{1, 1};

        boolean isCarParked = parkingAttendant.parkTheCar(car);
        int[] carParkedAtSpot = parkingAttendant.getSlotDetailsWhereCarIsParked(car);

        assertTrue(isCarParked);
        assertArrayEquals(carParkedAtSpot, expectedCarParkedAt);
    }

    @Test
    public void shouldTestForIfCarsAreParkedInEvenlyFashionAfterParkingAndUnParkingACar() {
        int carsToBeParked = 3;
        int totalSlots = 3, totalLots = 2;
        Car[] carNames = new Car[3];
        Car car = new Car("DL5CQ 0258");
        boolean[] carParkedStatus = new boolean[3];
        ParkingAttendant parkingAttendant = new ParkingAttendant(totalLots, totalSlots);


        parkTheCarsAndUpdateTheirStatus(carsToBeParked, carNames, carParkedStatus, parkingAttendant);


        boolean isCarUnParked = parkingAttendant.unParkTheCar(carNames[1]);
        boolean isCarParked = parkingAttendant.parkTheCar(car);
        int[] carParkedAtSpot = parkingAttendant.getSlotDetailsWhereCarIsParked(car);
        int[] expectedCarParkedAt = new int[]{1, 0};
        boolean[] expectedCarParked = new boolean[]{true, true, true};

        assertArrayEquals(carParkedStatus, expectedCarParked);
        assertTrue(isCarUnParked);
        assertTrue(isCarParked);
        assertArrayEquals(carParkedAtSpot, expectedCarParkedAt);
    }

    private void parkTheCarsAndUpdateTheirStatus(int carsToBeParked, Car[] carNames, boolean[] carParkedStatus, ParkingAttendant parkingAttendant) {
        for (int carsParked = 0; carsParked < carsToBeParked; carsParked++) {
            Car carPark = new Car("C" + carsParked);
            carNames[carsParked] = carPark;
            carParkedStatus[carsParked] = parkingAttendant.parkTheCar(carPark);
        }
    }
}