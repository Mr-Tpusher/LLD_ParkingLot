package v1.entity;

import v0.exceptions.SpotUnavailableException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ParkingLot {
    private String name;
    Map<String, EntryGate> entryGates;
    Map<String, ExitGate> exitGates;
    List<ParkingSpot> parkingSpots;
    ConcurrentMap<UUID, Ticket> tickets;

    public ParkingLot(String name) {
        this.name = name;
        this.entryGates = new HashMap<>();
        this.exitGates = new HashMap<>();
        this.parkingSpots = new ArrayList<>();
        this.tickets = new ConcurrentHashMap<>();

        // create 5 parking spots
        ConcurrentSkipListMap<Integer, ParkingSpot> gate1 = new ConcurrentSkipListMap<>();
        ConcurrentSkipListMap<Integer, ParkingSpot> gate2 = new ConcurrentSkipListMap<>();

        for (int i = 0; i < 5; i++) {
            ParkingSpot parkingSpot = new ParkingSpot("spot" + i);
            parkingSpots.add(parkingSpot);
            gate1.put(i, parkingSpot);
            gate2.put(5 - i, parkingSpot);
        }

        EntryGate entryGate1 = new EntryGate("entry1", gate1);
        EntryGate entryGate2 = new EntryGate("entry2" , gate2);

        // add gate3 with random distances
        ConcurrentSkipListMap<Integer, ParkingSpot> gate3 = new ConcurrentSkipListMap<>();
        gate3.put(1, parkingSpots.get(3));
        gate3.put(2, parkingSpots.get(1));
        gate3.put(3, parkingSpots.get(0));
        gate3.put(4, parkingSpots.get(4));
        gate3.put(5, parkingSpots.get(2));
        EntryGate entryGate3 = new EntryGate("entry3", gate3);


        this.entryGates.put(entryGate1.getId(), entryGate1);
        this.entryGates.put(entryGate2.getId(), entryGate2);
        this.entryGates.put(entryGate3.getId(), entryGate3);


        // Add exit gates
        this.exitGates.put("exit1", new ExitGate("exit1"));

    }

    public Ticket park(Vehicle vehicle, String gate) {
        // get an available spot from that particular gate
        EntryGate entryGate = entryGates.get(gate);
        ParkingSpot parkingSpot = entryGate.extractAvailableSpot();

        if (parkingSpot != null) {
            // reserver the spot
            parkingSpot.reserveSpot();

            // remove the spot from other gates
            for (Map.Entry<String, EntryGate> entry : entryGates.entrySet()) {
                EntryGate entryGate1 = entry.getValue();
                if (entryGate1 != entryGate) {
                    entryGate1.removeSpot(parkingSpot);
                }
            }

            Ticket ticket = new Ticket(vehicle, parkingSpot.getId());
            tickets.put(ticket.getId(), ticket);

            return ticket;
        } else {
            throw new SpotUnavailableException("No spot available right now.");
            // return null;
        }
    }
}
