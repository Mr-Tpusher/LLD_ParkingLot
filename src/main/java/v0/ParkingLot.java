package v0;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.TreeMap;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ParkingLot {
    private String name;
    private TreeMap<Integer, ParkingSpot> availableSpots;

    public ParkingLot(String name) {
        this.name = name;
    }

    public Ticket park(VehicleDetails vehicleDetails) {

        // consider our algo gave this parking spot
        ParkingSpot parkingSpot = new ParkingSpot("A1");
        parkingSpot.reserverSpot();

        return new Ticket(vehicleDetails, parkingSpot);
    }

    public double exit(UUID ticketId) {
        return 100;
    }
}
