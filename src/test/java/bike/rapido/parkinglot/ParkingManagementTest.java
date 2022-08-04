package bike.rapido.parkinglot;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class ParkingManagementTest {
    final int TOTAL_PARKING_SLOTS = 10;

    ParkingSlots parkingSlots;
    Driver driver;
    Car car;

    @Before
    public void setUp() {
        parkingSlots = new ParkingSlots(TOTAL_PARKING_SLOTS);
        driver = new Driver(TOTAL_PARKING_SLOTS);
        car = new Car("Testing the car");
    }

    @Test
    public void shouldAllowDriverToParkIfAvailableSlotsAreMoreThan1() {
        boolean hasDriverParked = driver.parkVehicle(car);

        assertThat(hasDriverParked, is(true));
    }

    @Test
    public void shouldNotAllowDriverToParkIfAvailableSlotsAre0() {
        int totalSlots = 0;
        Driver driver = new Driver(totalSlots);
        boolean hasDriverParked = driver.parkVehicle(car);

        assertThat(hasDriverParked, is(false));
    }

    @Test
    public void shouldAllowDriverToUnParkCar() {
        int totalSlots = 10;
        Driver driver = new Driver(totalSlots);

        boolean hasDriverParked = driver.parkVehicle(car);
        boolean hasDriverUnParked = driver.unParkVehicle(car);

        assertTrue(hasDriverParked);
        assertTrue(hasDriverUnParked);
    }

    @Test
    public void shouldNotAllowDriverToUnParkFromEmptyParkingSpace() {
        int totalSlots = 10;
        Driver driver = new Driver(totalSlots);

        boolean isParked = driver.parkVehicle(car);
        boolean hasUnparked = driver.unParkVehicle(car);
        boolean hasAnotherDriverUnParked = driver.unParkVehicle(car);

        assertTrue(isParked);
        assertTrue(hasUnparked);
        assertFalse(hasAnotherDriverUnParked);
    }

    @Test
    public void shouldNotifyOwnerIfParkingLotIsFull() {
        int totalSlots = 1;
        Driver driver = new Driver(totalSlots);
        // ParkingSlots parkingSlots = new ParkingSlots(totalSlots);
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();

        driver.getParkingSlot().registerForNotifyingLotIsFull(parkingLotOwner);
        boolean isCarParked = driver.parkVehicle(car);
        String signBoard = parkingLotOwner.getSignBoard();

        assertTrue(isCarParked);
        assertThat(signBoard, is("FULL"));
    }

    @Test
    public void shouldNotNotifyOwnerIfParkingLotIsNotFull() {
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();

        parkingSlots.registerForNotifyingLotIsFull(parkingLotOwner);
        boolean isCarParked = driver.parkVehicle(car);
        String signBoard = parkingLotOwner.getSignBoard();

        assertTrue(isCarParked);
        assertNull(signBoard);
    }

    @Test
    public void shouldNotifyAirportSecurityPersonalIfParkingLotIsFull() {
        int totalSlots = 1;
        Driver driver = new Driver(totalSlots);
        AirportSecurityPersonal airportSecurityPersonal = new AirportSecurityPersonal();

        driver.getParkingSlot().registerForNotifyingLotIsFull(airportSecurityPersonal);
        boolean hasDriverParked = driver.parkVehicle(car);
        boolean hasSecurityPersonalNotified = airportSecurityPersonal.isSecurityPersonalNotified();

        assertTrue(hasDriverParked);
        assertTrue(hasSecurityPersonalNotified);
    }

    @Test
    public void shouldNotNotifyAirportSecurityPersonalIfParkingLotIsNotFull() {
        AirportSecurityPersonal airportSecurityPersonal = new AirportSecurityPersonal();

        parkingSlots.registerForNotifyingLotIsFull(airportSecurityPersonal);
        boolean isCarParked = driver.parkVehicle(car);
        boolean hasSecurityPersonalNotified = airportSecurityPersonal.isSecurityPersonalNotified();

        assertTrue(isCarParked);
        assertFalse(hasSecurityPersonalNotified);
    }

    @Test
    public void shouldNotifyOwnerIfParkingLotHasSpaceAgain() {
        final int totalSlots = 1;
        Driver driver = new Driver(totalSlots);
        Car car = new Car("testing");
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();

        driver.getParkingSlot().registerForNotifyingWhenLotHasSpaceAgain(parkingLotOwner);
        boolean isCarParkd = driver.parkVehicle(car);
        boolean isCarUnparked = driver.unParkVehicle(car);

        String signBoard = parkingLotOwner.getSignBoard();

        assertTrue(isCarParkd);
        assertTrue(isCarUnparked);
        assertNull(signBoard);
    }
}