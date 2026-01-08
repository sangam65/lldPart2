package parkinglot.entities;

import java.util.UUID;

import parkinglot.entities.vehicles.VehicleInterface;
import parkinglot.enums.VehicleType;

public class ParkingSpot {
    private final String parkingSpotId;
    private final VehicleType vehicleType;
    private boolean occupied;
    private VehicleInterface parkedVehicle;

    public ParkingSpot(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        this.parkingSpotId = UUID.randomUUID().toString();
        this.occupied = false;
        this.parkedVehicle = null;
    }

    public synchronized boolean canParkVehicle(VehicleInterface vehicleInterface) {

        return !this.occupied && vehicleInterface.getVehcileType().equals(this.vehicleType);
    }

    public synchronized void parkVehicle(VehicleInterface vehicleInterface) {

        this.occupied = true;
        this.parkedVehicle = vehicleInterface;

    }

    public synchronized void unparkVehicle() {
        this.occupied = false;
        this.parkedVehicle = null;
    }

    public synchronized boolean isAvailable() {
        return !occupied;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public VehicleInterface getParkedVehicle() {
        return parkedVehicle;
    }

}
