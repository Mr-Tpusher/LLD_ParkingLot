package v1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParkingSpot {
    private String id;
    private boolean isReserved;

    public ParkingSpot(String id) {
        this.id = id;
        this.isReserved = false;
    }

    public synchronized boolean reserveSpot() {

        if (!isReserved) {
            this.isReserved = true;
            return true;
        } else {
            return false;
        }
    }


    public synchronized void freeSpot() {
        this.isReserved = false;
    }
}
