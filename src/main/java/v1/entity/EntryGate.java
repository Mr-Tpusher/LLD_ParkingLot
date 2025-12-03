package v1.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Function;

@Getter
@Setter
@ToString
public class EntryGate {
    private String id;
    private ConcurrentHashMap<ParkingSpot, Integer> parkingSpotDistance;
    private ConcurrentSkipListMap<Integer, ParkingSpot> availableSpots;

    public EntryGate(String id, ConcurrentSkipListMap<Integer, ParkingSpot> spots) {
        this.id = id;
        this.availableSpots = spots;
        parkingSpotDistance = new ConcurrentHashMap<>();

        for (Map.Entry<Integer, ParkingSpot> entry : availableSpots.entrySet()) {
            parkingSpotDistance.put(entry.getValue(), entry.getKey());
        }
    }

    public synchronized ParkingSpot extractAvailableSpot() {
        Map.Entry<Integer, ParkingSpot> entry = availableSpots.pollFirstEntry();
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    public void removeSpot(ParkingSpot parkingSpot) {
        this.availableSpots.remove(parkingSpotDistance.get(parkingSpot));
    }
}
