package v1;

import v1.entity.ParkingLot;
import v1.entity.Ticket;
import v1.entity.Vehicle;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot("ParkIt!!!");


        // case1: closest to the entry gate test
        Vehicle vehicle1 = new Vehicle("1111");
        Ticket ticket = parkingLot.park(vehicle1, "entry1");
        System.out.println(ticket);

        Vehicle vehicle2 = new Vehicle("2222");
        Ticket ticket2 = parkingLot.park(vehicle2, "entry2");
        System.out.println(ticket2);

        Vehicle vehicle3 = new Vehicle("3333");
        Ticket ticket3 = parkingLot.park(vehicle3, "entry3");
        System.out.println(ticket3);

        Vehicle vehicle4 = new Vehicle("4444");
        Ticket ticket4 = parkingLot.park(vehicle4, "entry2");
        System.out.println(ticket4);


        // concurrency check
        testConcurrency(parkingLot);

    }

    public static void testConcurrency(ParkingLot parkingLot) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // gate 1
        Vehicle vehicle1 = new Vehicle("A1111");
        WorkerThread thread1 = new WorkerThread(parkingLot, vehicle1, "entry1", countDownLatch);
        thread1.start();

        // gate2
        Vehicle vehicle2 = new Vehicle("B1111");
        WorkerThread thread2 = new WorkerThread(parkingLot, vehicle2, "entry2", countDownLatch);
        thread2.start();

        // gate3
        Vehicle vehicle3 = new Vehicle("C1111");
        WorkerThread thread3 = new WorkerThread(parkingLot, vehicle3, "entry3", countDownLatch);
        thread3.start();


        // all 3 threads waiting at the await function.
        // Let's release all 3 thread together
        countDownLatch.countDown();


    }
}


class WorkerThread extends Thread {
    private CountDownLatch countDownLatch;
    private ParkingLot parkingLot;
    private Vehicle vehicle;
    private String gate;

    WorkerThread(ParkingLot parkingLot, Vehicle vehicle, String gate, CountDownLatch countDownLatch) {
        this.parkingLot = parkingLot;
        this.countDownLatch = countDownLatch;
        this.vehicle = vehicle;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(vehicle + " at " + gate + " waiting for parking...");
        try {
            countDownLatch.await();
            Ticket ticket = parkingLot.park(vehicle, gate);
            System.out.println(ticket);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
