package v0;

import v0.constants.VehicleSize;

public class Main {
    public static void main(String[] args) {

        ParkingLot parkingLot = new ParkingLot("ParkHere");

        VehicleDetails vehicleDetails = new VehicleDetails("AB12 CD4567",
                VehicleSize.S);

        Ticket ticket = parkingLot.park(vehicleDetails);
        System.out.println(ticket);

        //double amount = parkingLot.exit(ticket);

        //System.out.println("Please pay amount = " + amount);

        //-------------------

        // park another
        Ticket ticket1 = parkingLot.park(new VehicleDetails("abcd", VehicleSize.S));
        System.out.println(ticket1);

        // --------------------

        // exit one and park another
        //double amount = parkingLot.exit(ticket);
        double amount = parkingLot.exit(ticket1);
        System.out.println("Pay=" + amount);

        Ticket ticket2 = parkingLot.park(new VehicleDetails("xyz", VehicleSize.S));
        System.out.println(ticket2);

    }
}
