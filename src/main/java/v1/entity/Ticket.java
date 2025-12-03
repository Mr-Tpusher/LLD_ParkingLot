package v1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Ticket {
    private UUID id;
    private Vehicle vehicle;
    private String parkingSpotId;

    public Ticket(Vehicle vehicle, String parkingSpotId) {
        this.id = UUID.randomUUID();
        this.vehicle = vehicle;
        this.parkingSpotId = parkingSpotId;
    }
}
