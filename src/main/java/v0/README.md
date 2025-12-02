### v0 requirements

Design a ParkingLot 
- One level
- One entry
- One exit
- Allocate a spot nearest to the entry
- Charge on hourly basis
- Spots are of 3 sizes S,M,L
- Vehicles are also of 3 sizes S,M,L
- A vehicle can be parked in a larger spot if all similar spots are occupied


### APIs

```Ticket ticket = park(VehicleDetails)```

```double amount = exit(ticketId)```


### Entities
- ParkingLot
- EntryGate (not needed)
- ExitGat (not needed)
- ParkingSpot
- VehicleDetails
- Ticket
- VehicleSize
- ParkingSpotSize
