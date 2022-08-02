package bike.rapido.parkinglot;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingLotTest {
    @Test
    public void shouldAllowDriverToParkIfAvailableSlotsAreMoreThan1() {
        int totalSlots = 10;

        new ParkingSpace(totalSlots);
        Driver driver = new Driver();
        boolean hasDriverParked = driver.checkAvailabilityAndPark();

        assertThat(hasDriverParked, is(true));
    }

    @Test
    public void shouldNotAllowDriverToParkIfAvailableSlotsAre0() {
        int totalSlots = 1;

        new ParkingSpace(totalSlots);
        new Driver().checkAvailabilityAndPark();
        boolean hasDriverParked = new Driver().checkAvailabilityAndPark();

        assertThat(hasDriverParked, is(false));
    }

    @Test
    public void shouldAllowDriverToUnParkCar() {
        int totalSlots = 10;

        new ParkingSpace(totalSlots);
        Driver driver = new Driver();
        driver.checkAvailabilityAndPark();
        boolean hasDriverUnParked = driver.unParkTheCar();

        assertThat(hasDriverUnParked, is(true));
    }

    @Test
    public void shouldNotAllowDriverToUnParkFromEmptyParkingSpace() {
        int totalSlots = 10;

        new ParkingSpace(totalSlots);
        Driver driver = new Driver();
        driver.checkAvailabilityAndPark();
        driver.unParkTheCar();
        boolean hasAnotherDriverUnParked = new Driver().unParkTheCar();

        assertThat(hasAnotherDriverUnParked, is(false));
    }

    @Test
    public void shouldNotifyOwnerIfParkingLotIsFull() {
        int totalSlots = 1;

        new ParkingSpace(totalSlots);
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingSpace.registerForNotifyingLotIsFull(parkingLotOwner);
        new Driver().checkAvailabilityAndPark();

        String signBoard = parkingLotOwner.getSignBoard();

        assertThat(signBoard, is("FULL"));
    }

    @Test
    public void shouldNotNotifyOwnerIfParkingLotIsNotFull() {
        int totalSlots = 5;

        new ParkingSpace(totalSlots);
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingSpace.registerForNotifyingLotIsFull(parkingLotOwner);
        new Driver().checkAvailabilityAndPark();

        String signBoard = parkingLotOwner.getSignBoard();

        assertNull(signBoard);
    }

    @Test
    public void shouldNotifyAirportSecurityPersonalIfParkingLotIsFull() {
        int totalSlots =1;

        new ParkingSpace(totalSlots);
        AirportSecurityPersonal airportSecurityPersonal = new AirportSecurityPersonal();
        ParkingSpace.registerForNotifyingLotIsFull(airportSecurityPersonal);
        new Driver().checkAvailabilityAndPark();

        boolean hasSecurityPersonalNotified = airportSecurityPersonal.isSecurityPersonalNotified();

        assertThat(hasSecurityPersonalNotified, is(true));
    }

    @Test
    public void shouldNotNotifyAirportSecurityPersonalIfParkingLotIsNotFull() {
        int totalSlots =5;

        new ParkingSpace(totalSlots);
        AirportSecurityPersonal airportSecurityPersonal = new AirportSecurityPersonal();
        ParkingSpace.registerForNotifyingLotIsFull(airportSecurityPersonal);
        new Driver().checkAvailabilityAndPark();

        boolean hasSecurityPersonalNotified = airportSecurityPersonal.isSecurityPersonalNotified();

        assertThat(hasSecurityPersonalNotified, is(false));
    }

    @Test
    public void shouldNotifyOwnerIfParkingLotHasSpaceAgain() {
        int totalSlots = 1;

        new ParkingSpace(totalSlots);
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        ParkingSpace.registerForNotifyingWhenLotHasSpaceAgain(parkingLotOwner);

        Driver driver = new Driver();
        driver.checkAvailabilityAndPark();
        driver.unParkTheCar();

        String signBoard = parkingLotOwner.getSignBoard();

        assertNull(signBoard);
    }
}