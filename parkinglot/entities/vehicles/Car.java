package parkinglot.entities.vehicles;

import parkinglot.enums.VehicleType;

public class Car implements VehicleInterface {
       private final VehicleType vehicleType;
    private final String vehicleNumber;
   
    private final int wheels;
     public Car(String vehicleNumber) {
        this.vehicleType = VehicleType.CAR;
        this.vehicleNumber =vehicleNumber;
        this.wheels = 4;
    }

    @Override
    public VehicleType getVehcileType() {
        return vehicleType;
    }

    @Override
    public int getWheels() {
        return wheels;
    }

    @Override
    public String getVehcileNumber() {
        return vehicleNumber;
    }

}
