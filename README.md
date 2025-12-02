# LLD of a Parking Lot

## Requirements
- Design a Parking Lot
- Multilevel
- One entry gate, One exit gate at the ground level
- Three sizes of slot S,M,L
- Three types of Vehicles S,M,L
- Parking spot should be allocated closest to the entry gate
- Smaller vehicles can fit into larger ones if all smaller spots are occupied
- There's base charge for each type of vehicle


## Future Scope
- Multiple entry and exit gates(each level)
- Different parking strategies would come into picture
- Different charging strategies would come into picture


## APIs
``` Ticket ticket = park(VehicleDetails)```  
``` Double amount = exit(ticketId) ```