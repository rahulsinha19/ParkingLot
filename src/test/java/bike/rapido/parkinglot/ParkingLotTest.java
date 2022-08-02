package bike.rapido.parkinglot;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
    public void notifyOwnerIfParkingLotIsFull() {
        int totalSlots = 1;

        new ParkingSpace(totalSlots);
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        new Driver().checkAvailabilityAndPark();


        boolean hasOwnerNotified = parkingLotOwner.isOwnerNotified();

        assertThat(hasOwnerNotified, is(true));
    }

    @Test
    public void doNotNotifyOwnerIfParkingLotIsNotFull() {
        int totalSlots = 5;

        new ParkingSpace(totalSlots);
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
        new Driver().checkAvailabilityAndPark();


        boolean hasOwnerNotified = parkingLotOwner.isOwnerNotified();

        assertThat(hasOwnerNotified, is(false));
    }

    @Test
    public void notifyAirportSecurityPersonalIfParkingLotIsFull() {
        int totalSlots =1;

        new ParkingSpace(totalSlots);
        AirportSecurityPersonal airportSecurityPersonal = new AirportSecurityPersonal();
        new Driver().checkAvailabilityAndPark();

        boolean hasSecurityPersonalNotified = airportSecurityPersonal.isSecurityPersonalNotified();

        assertThat(hasSecurityPersonalNotified, is(true));
    }

    @Test
    public void doNotNotifyAirportSecurityPersonalIfParkingLotIsNotFull() {
        int totalSlots =5;

        new ParkingSpace(totalSlots);
        AirportSecurityPersonal airportSecurityPersonal = new AirportSecurityPersonal();
        new Driver().checkAvailabilityAndPark();

        boolean hasSecurityPersonalNotified = airportSecurityPersonal.isSecurityPersonalNotified();

        assertThat(hasSecurityPersonalNotified, is(false));
    }
}