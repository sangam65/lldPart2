package parkinglot.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import parkinglot.enums.VehicleType;

public class ParkingFloor {
    private final HashMap<String,ParkingSpot>parkingSpots;
    private final int floorNumber;

   

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getVacantParkingSpotsForVehcile(VehicleType vehicleType) {
        List<ParkingSpot>vacantParkingSpots=new ArrayList<>();
        for(ParkingSpot parkingSpot:parkingSpots.values()){
            if(parkingSpot.isAvailable()&&parkingSpot.getVehicleType().equals(vehicleType)){
                vacantParkingSpots.add(parkingSpot);
            }
        }
        return vacantParkingSpots;
    }

    public ParkingFloor(int floorNumber) {
        this.parkingSpots = new HashMap<>();
        this.floorNumber=floorNumber;
    }
    public void displayAvailability(){
          System.out.printf("--- Floor %d Availability ---\n", floorNumber);
          
    }
    public void addSpot(ParkingSpot spot) {
        parkingSpots.put(spot.getParkingSpotId(), spot);
    }
}
