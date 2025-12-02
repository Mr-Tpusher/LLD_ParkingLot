package v0;

import v0.constants.VehicleSize;
import v0.exceptions.SpotUnavailableException;

import java.util.HashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main_v01 {
    public static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLot("ParkIt!!!");
        HashMap<String, Ticket> tickets = new HashMap<>();


        for (int i = 0; i < 10; i++) {
            VehicleDetails vehicleDetails = new VehicleDetails("ABCD" + i, VehicleSize.S);
            Ticket ticket = parkingLot.park(vehicleDetails);
            tickets.put(vehicleDetails.getRegistrationNumber(), ticket);
            System.out.println(ticket);
        }

        Double d = parkingLot.exit(tickets.get("ABCD3"));

        Ticket ticket = parkingLot.park(new VehicleDetails("xyz", VehicleSize.S));
        System.out.println(ticket);

    }
}
