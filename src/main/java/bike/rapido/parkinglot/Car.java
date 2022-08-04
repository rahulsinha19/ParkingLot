package bike.rapido.parkinglot;

public class Car {

    private final String number;

    public Car(final String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNumber='" + number + '\'' +
                '}';
    }
}
