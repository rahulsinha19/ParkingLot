package bike.rapido.parkinglot;

public class AirportSecurityPersonal implements Observer {
    private boolean hasSecurityPersonalNotified = false;
    public AirportSecurityPersonal() {
        register();
    }

    public void redirectSecurityStaff() {
        this.hasSecurityPersonalNotified = true;
    }

    public boolean isSecurityPersonalNotified() {
        return this.hasSecurityPersonalNotified;
    }

    @Override
    public void register() {
        ParkingSpace.addObserver(this);
    }

    @Override
    public void notifyObserver() {
        redirectSecurityStaff();
    }
}
