package v0;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParkingSpot {
    private String name;
    private boolean isAvailable;

    public ParkingSpot(String name) {
        this.name = name;
        this.isAvailable = true;
    }

    public boolean reserverSpot() {
        if (this.isAvailable) {
            this.isAvailable = false;
            return true;
        } else {
            return false;
        }
    }
}
