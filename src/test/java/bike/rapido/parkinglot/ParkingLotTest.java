package bike.rapido.parkinglot;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingLotTest {

    @Test
    public void driverCanParkAtFirstSlotIfAvailableSlotsAreMoreThan1() {
        int totalSlots = 10;
        new ParkingSpace(totalSlots);
        Driver driver = new Driver();

        boolean hasDriverParked = driver.checkAvailabilityAndPark();

        assertThat(hasDriverParked, is(true));
    }

    @Test
    public void canDriverParkAtSecondSlotIfTotalSlotsAre1() {
        int totalSlots = 1;
        new ParkingSpace(totalSlots);

        new Driver().checkAvailabilityAndPark();

        boolean hasDriverParked = new Driver().checkAvailabilityAndPark();

        assertThat(hasDriverParked, is(false));
    }
}