package v0;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import v0.exceptions.SpotUnavailableException;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    public synchronized Ticket park(VehicleDetails vehicleDetails) throws SpotUnavailableException {
        // Iterate over all the available spots and find the first nearest
        for (Map.Entry<Integer, ParkingSpot> entry : availableSpots.entrySet()) {
            ParkingSpot parkingSpot = entry.getValue();

            // try to reserver the spot using its own lock
            if (parkingSpot.reserverSpot()) {
                // if successfully reserved, remove it from available spots
                synchronized (this) {
                    availableSpots.remove(entry.getKey());
                }
                return new Ticket(vehicleDetails, parkingSpot);
            }
        }
            throw new SpotUnavailableException("No spots are available at the moment");
    }

    public synchronized double exit(Ticket ticket) {
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.freeSpot();

        synchronized (this) {
            availableSpots.put(spotDistance.get(parkingSpot), parkingSpot);
        }

        return 100;
    }
}
