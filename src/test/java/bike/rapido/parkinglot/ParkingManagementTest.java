package bike.rapido.parkinglot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
        boolean hasUnParked = driver.unParkVehicle(car);
        boolean hasAnotherDriverUnParked = driver.unParkVehicle(car);

        assertTrue(isParked);
        assertTrue(hasUnParked);
        assertFalse(hasAnotherDriverUnParked);
    }

    @Test
    public void shouldNotifyOwnerIfParkingLotIsFull() {
        int totalSlots = 1;
        Driver driver = new Driver(totalSlots);
        ParkingLotOwner parkingLotOwnerSpy = Mockito.spy(new ParkingLotOwner());

        driver.getParkingSlot().registerForNotifyingLotIsFull(parkingLotOwnerSpy);
        boolean isCarParked = driver.parkVehicle(car);
        String signBoard = parkingLotOwnerSpy.getSignBoard();

        assertTrue(isCarParked);
        assertThat(signBoard, is("FULL"));
        Mockito.verify(parkingLotOwnerSpy).notifyObserverWhenLotIsFull();
    }

    @Test
    public void shouldNotNotifyOwnerIfParkingLotIsNotFull() {
        ParkingLotOwner parkingLotOwnerSpy = Mockito.spy(new ParkingLotOwner());

        parkingSlots.registerForNotifyingLotIsFull(parkingLotOwnerSpy);
        boolean isCarParked = driver.parkVehicle(car);
        String signBoard = parkingLotOwnerSpy.getSignBoard();

        assertTrue(isCarParked);
        assertNull(signBoard);
        Mockito.verify(parkingLotOwnerSpy, Mockito.times(0)).notifyObserverWhenLotHasSpaceAgain();
    }

    @Test
    public void shouldNotifyAirportSecurityPersonalIfParkingLotIsFull() {
        int totalSlots = 1;
        Driver driver = new Driver(totalSlots);
        AirportSecurityPersonal airportSecurityPersonalSpy = Mockito.spy(new AirportSecurityPersonal());

        driver.getParkingSlot().registerForNotifyingLotIsFull(airportSecurityPersonalSpy);
        boolean hasDriverParked = driver.parkVehicle(car);
        boolean hasSecurityPersonalNotified = airportSecurityPersonalSpy.isSecurityPersonalNotified();

        assertTrue(hasDriverParked);
        assertTrue(hasSecurityPersonalNotified);
        Mockito.verify(airportSecurityPersonalSpy).notifyObserverWhenLotIsFull();
        Mockito.verify(airportSecurityPersonalSpy).isSecurityPersonalNotified();
    }

    @Test
    public void shouldNotNotifyAirportSecurityPersonalIfParkingLotIsNotFull() {
        AirportSecurityPersonal airportSecurityPersonalSpy = Mockito.spy(new AirportSecurityPersonal());

        parkingSlots.registerForNotifyingLotIsFull(airportSecurityPersonalSpy);
        boolean isCarParked = driver.parkVehicle(car);
        boolean hasSecurityPersonalNotified = airportSecurityPersonalSpy.isSecurityPersonalNotified();

        assertTrue(isCarParked);
        assertFalse(hasSecurityPersonalNotified);
        Mockito.verify(airportSecurityPersonalSpy).isSecurityPersonalNotified();
        Mockito.verify(airportSecurityPersonalSpy, Mockito.times(0)).notifyObserverWhenLotHasSpaceAgain();
    }

    @Test
    public void shouldNotifyOwnerIfParkingLotHasSpaceAgain() {
        final int totalSlots = 1;
        Driver driver = new Driver(totalSlots);
        Car car = new Car("testing");
        ParkingLotOwner parkingLotOwnerSpy = Mockito.spy(new ParkingLotOwner());

        driver.getParkingSlot().registerForNotifyingWhenLotHasSpaceAgain(parkingLotOwnerSpy);
        boolean isCarParked = driver.parkVehicle(car);
        boolean isCarUnParked = driver.unParkVehicle(car);

        String signBoard = parkingLotOwnerSpy.getSignBoard();

        assertTrue(isCarParked);
        assertTrue(isCarUnParked);
        assertNull(signBoard);
    }

}