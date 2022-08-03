package bike.rapido.parkinglot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingLotTest {
	final int TOTAL_PARKING_SLOTS = 10;

	ParkingSlots parkingSlots;
	Driver driver;

	@Before
	public void setUp() {
		parkingSlots = new ParkingSlots(TOTAL_PARKING_SLOTS);
		driver = new Driver(TOTAL_PARKING_SLOTS);
	}

	@Test
	public void shouldAllowDriverToParkIfAvailableSlotsAreMoreThan1() {
		boolean hasDriverParked = driver.parkVehicle();

		assertThat(hasDriverParked, is(true));
	}

	@Test
	public void shouldNotAllowDriverToParkIfAvailableSlotsAre0() {
		int totalSlots = 0;
		Driver driver = new Driver(totalSlots);
		boolean hasDriverParked = driver.parkVehicle();

		assertThat(hasDriverParked, is(false));
	}

	@Test
	public void shouldAllowDriverToUnParkCar() {
		int totalSlots = 10;
		Driver driver = new Driver(totalSlots);

		boolean hasDriverParked = driver.parkVehicle();
		boolean hasDriverUnParked = driver.unParkVehicle();

		assertTrue(hasDriverParked);
		assertTrue(hasDriverUnParked);
	}

	@Test
	public void shouldNotAllowDriverToUnParkFromEmptyParkingSpace() {
		int totalSlots = 10;
		Driver driver = new Driver(totalSlots);

		boolean isParked = driver.parkVehicle();
		boolean hasUnparked = driver.unParkVehicle();
		boolean hasAnotherDriverUnParked = driver.unParkVehicle();

		assertTrue(isParked);
		assertTrue(hasUnparked);
		assertFalse(hasAnotherDriverUnParked);
	}

	@Test
	public void shouldNotifyOwnerIfParkingLotIsFull() {
		int totalSlots = 1;
		Driver driver = new Driver(totalSlots);
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();

		ParkingSlots.registerForNotifyingLotIsFull(parkingLotOwner);
		boolean isCarParked = driver.parkVehicle();
		String signBoard = parkingLotOwner.getSignBoard();

		assertTrue(isCarParked);
		assertThat(signBoard, is("FULL"));
	}

	@Test
	public void shouldNotNotifyOwnerIfParkingLotIsNotFull() {
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();
		ParkingSlots.registerForNotifyingLotIsFull(parkingLotOwner);

		boolean isCarParked = driver.parkVehicle();
		String signBoard = parkingLotOwner.getSignBoard();

		assertTrue(isCarParked);
		assertNull(signBoard);
	}

	@Test
	public void shouldNotifyAirportSecurityPersonalIfParkingLotIsFull() {
		int totalSlots = 1;

		Driver driver = new Driver(totalSlots);

		AirportSecurityPersonal airportSecurityPersonal = new AirportSecurityPersonal();
		ParkingSlots.registerForNotifyingLotIsFull(airportSecurityPersonal);

		boolean hasDriverParked = driver.parkVehicle();

		boolean hasSecurityPersonalNotified = airportSecurityPersonal.isSecurityPersonalNotified();

		assertTrue(hasDriverParked);
		assertTrue(hasSecurityPersonalNotified);
	}

	@Test
	public void shouldNotNotifyAirportSecurityPersonalIfParkingLotIsNotFull() {
		AirportSecurityPersonal airportSecurityPersonal = new AirportSecurityPersonal();

		ParkingSlots.registerForNotifyingLotIsFull(airportSecurityPersonal);
		boolean isCarParked = driver.parkVehicle();

		boolean hasSecurityPersonalNotified = airportSecurityPersonal.isSecurityPersonalNotified();

		assertTrue(isCarParked);
		assertFalse(hasSecurityPersonalNotified);
	}

	@Test
	public void shouldNotifyOwnerIfParkingLotHasSpaceAgain() {
		final int totalSlots = 1;
		Driver driver = new Driver(totalSlots);
		ParkingLotOwner parkingLotOwner = new ParkingLotOwner();

		ParkingSlots.registerForNotifyingWhenLotHasSpaceAgain(parkingLotOwner);
		boolean isCarParkd = driver.parkVehicle();
		boolean isCarUnparked = driver.unParkVehicle();

		String signBoard = parkingLotOwner.getSignBoard();

		assertTrue(isCarParkd);
		assertTrue(isCarUnparked);
		assertNull(signBoard);
	}
}
