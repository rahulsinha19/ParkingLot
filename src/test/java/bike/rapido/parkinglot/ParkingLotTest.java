package bike.rapido.parkinglot;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingLotTest {

    @Test
    public void canDriverParkIfTotalSlotsAreMoreThan1() {
        int totalSlots = 10;

        new ParkingSpace(totalSlots);
        Driver driver = new Driver();
        boolean hasDriverParked = driver.checkAvailabilityAndPark();

        assertThat(hasDriverParked, is(true));
    }

    @Test
    public void canSecondDriverParkIfTotalSlotsAre1() {
        int totalSlots = 1;

        new ParkingSpace(totalSlots);
        new Driver().checkAvailabilityAndPark();
        boolean hasDriverParked = new Driver().checkAvailabilityAndPark();

        assertThat(hasDriverParked, is(false));
    }

    @Test
    public void canDriverUnParkCar() {
        int totalSlots = 10;

        new ParkingSpace(totalSlots);
        new Driver().checkAvailabilityAndPark();
        boolean hasDriverUnParked = new Driver().unPark();

        assertThat(hasDriverUnParked, is(true));
    }

    @Test
    public void canDriverUnParkFromEmptyParkingSpace() {
        int totalSlots = 10;

        new ParkingSpace(totalSlots);
        Driver driver1 = new Driver();
        driver1.checkAvailabilityAndPark();
        driver1.unPark();
        boolean hasAnotherDriverUnParked = new Driver().unPark();

        assertThat(hasAnotherDriverUnParked, is(false));

    }
}