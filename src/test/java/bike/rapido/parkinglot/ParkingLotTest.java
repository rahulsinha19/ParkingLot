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
    public void ownerShouldPutFullSignBoardIfParkingLotIsFull() {
        int totalSlots = 1;

        ParkingSpace parkingSpace = new ParkingSpace(totalSlots);
        new Driver().checkAvailabilityAndPark();
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner(parkingSpace);
        String signBoard = parkingLotOwner.putSignBoard();

        assertThat(signBoard, is("FULL"));
    }
    @Test
    public void ownerShouldPutNotFullSignBoardIfParkingLotIsNotFull() {
        int totalSlots = 2;

        ParkingSpace parkingSpace = new ParkingSpace(totalSlots);
        new Driver().checkAvailabilityAndPark();
        ParkingLotOwner parkingLotOwner = new ParkingLotOwner(parkingSpace);
        String signBoard = parkingLotOwner.putSignBoard();

        assertThat(signBoard, is("NOT FULL"));
    }
}