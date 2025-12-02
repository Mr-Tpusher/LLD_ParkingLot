package v0;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ParkingLot {
    private String name;
    private TreeMap<Integer, ParkingSpot> availableSpots;
    private Map<ParkingSpot, Integer> spotDistance;

    public ParkingLot(String name) {
        this.name = name;
        availableSpots = new TreeMap<>();
        spotDistance = new HashMap<>();
        populateParkingSpots();
    }

    public void populateParkingSpots() {
        for (int i = 0; i < 10; i++) {
            ParkingSpot parkingSpot = new ParkingSpot("A" + i);

            availableSpots.put(i+1,parkingSpot);
            spotDistance.put(parkingSpot, i + 1);
        }
    }

    public Ticket park(VehicleDetails vehicleDetails) {

        // return nearest to the entry
        Map.Entry<Integer, ParkingSpot> entry = availableSpots.firstEntry();
        ParkingSpot parkingSpot = entry.getValue();
        parkingSpot.reserverSpot();
        availableSpots.remove(entry.getKey());

        return new Ticket(vehicleDetails, parkingSpot);
    }

    public double exit(Ticket ticket) {
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        availableSpots.put(spotDistance.get(parkingSpot), parkingSpot );
        return 100;
    }
}
