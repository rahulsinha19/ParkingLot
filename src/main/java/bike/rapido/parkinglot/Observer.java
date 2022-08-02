package bike.rapido.parkinglot;

public interface Observer {
    void notifyObserverWhenLotIsFull();

    void notifyObserverWhenLotHasSpaceAgain();
}