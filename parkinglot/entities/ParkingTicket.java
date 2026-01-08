package parkinglot.entities;


import java.time.LocalDate;
import java.util.UUID;

import parkinglot.entities.vehicles.VehicleInterface;


public class ParkingTicket {
    private final String parkingTicketId;
    private final VehicleInterface vehicleInterface;
    private final LocalDate parkingTime;
    private final ParkingSpot parkingSpot;
    private LocalDate exitTime;
    public VehicleInterface getVehicleInterface() {
        return vehicleInterface;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
  

    
    public LocalDate getExitTime() {
        return exitTime;
    }
    public void setExitTime(LocalDate exitTime) {
        this.exitTime = exitTime;
    }
    
    public LocalDate getParkingTime() {
        return parkingTime;
    }
    public ParkingTicket( VehicleInterface vehicleInterface,ParkingSpot parkingSpot) {
        this.parkingSpot=parkingSpot;
        this.vehicleInterface=vehicleInterface;
        this.parkingTime=LocalDate.now();
        this.parkingTicketId=UUID.randomUUID().toString();
       

    }
    public String getParkingTicketId() {
        return parkingTicketId;
    }
   
    
}
