package v0;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Ticket {
    private UUID id;
    private VehicleDetails vehicleDetails;
    private ParkingSpot parkingSpot;

    public Ticket(VehicleDetails vehicleDetails, ParkingSpot parkingSpot) {
        this.id = UUID.randomUUID();
        this.vehicleDetails = vehicleDetails;
        this.parkingSpot = parkingSpot;
    }
}
