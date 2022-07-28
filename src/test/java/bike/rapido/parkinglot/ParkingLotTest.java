package bike.rapido.parkinglot;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingLotTest {
    @Test
    public void whetherTheDriverCanParkAt201() {
        String spaceId = "201";

        boolean isParkingSpaceAvailable = new Driver().park(spaceId);

        assertThat(isParkingSpaceAvailable, is(true));
    }

    @Test
    public void whetherAnotherDriverCanParkAt201() {
        String spaceId = "201";

        boolean isParkingSpaceAvailable = new Driver().park(spaceId);

        assertThat(isParkingSpaceAvailable, is(true));
    }


    @Test
    public void whetherTheDriverCanParkAt567() {
        String spaceId = "567";

        boolean isParkingSpaceAvailable = new Driver().park(spaceId);

        assertThat(isParkingSpaceAvailable, is(true));
    } 
}