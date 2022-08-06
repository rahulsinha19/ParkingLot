package bike.rapido.parkinglot;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CarTest {
    public static final String CAR_NUMBER = "Test Car 1234";
    private Car car;

    @Before
    public void setup() {
        car = new Car(CAR_NUMBER);
    }

    @Test
    public void shouldReturnDetailsOfCar() {
        final String receivedCarDetails = car.toString();

        assertTrue(receivedCarDetails.contains(CAR_NUMBER));
    }
}