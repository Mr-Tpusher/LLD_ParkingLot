package v0;

import v0.constants.VehicleSize;

public class Main {
    public static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLot("ParkHere");

        VehicleDetails vehicleDetails = new VehicleDetails("AB12 CD4567",
                VehicleSize.S);

        Ticket ticket = parkingLot.park(vehicleDetails);
        System.out.println(ticket);

        double amount = parkingLot.exit(ticket.getId());

        System.out.println("Please pay amount = " + amount);
    }
}
