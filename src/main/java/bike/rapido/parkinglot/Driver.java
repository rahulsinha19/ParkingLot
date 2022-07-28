package bike.rapido.parkinglot;

import java.util.HashMap;

class ParkingSpace {
    HashMap<String, Boolean> data = new HashMap<>();

    public boolean isEmpty(String spaceId) {
        if(data.get(spaceId) == null) {
            return true;
        } else {
            return false;
        }
    }

    public void add(String spaceId) {
        data.put(spaceId, true);
    }
}

public class Driver {
    ParkingSpace parkingSpace = new ParkingSpace();

    public boolean park(String spaceId) {
        if(parkingSpace.isEmpty(spaceId)) {
            parkingSpace.add(spaceId);
            return true;
        } else {
            return false;
        }
    }
}