### Requirements

- Single level parking lot
- Multiple entry and exit gates
- Different strategies for spot allocation
  - Nearest to the entry gate
  - Nearest to the exit gate
- Different charging strategies
  - Base hourly charge for weekdays
  - Weekend has separate charging strategy 
  - Nighttime has separate charging strategy
  

### APIs
``` Ticket park(VehicleDetails vehicleDetails) ```
``` Double exit(Long ticketId) ```


### entities
- ParkingLot
- EntryGate
- ExitGate
- ParkingSpot
- Ticket
- VehicleDetails
- IParkingStrategy
  - NearestToTheEntrance
  - NearestToTheExit
-IChargingStrategy
  - WeekdayChargingStrategy
  - WeekendChargingStrategy
  - NighttimeChargingStrategy