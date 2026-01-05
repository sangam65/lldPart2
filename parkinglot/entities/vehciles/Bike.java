package parkinglot.entities.vehciles;

import parkinglot.enums.VehicleType;

public class Bike  implements VehcileInterface{
    private final VehicleType vehicleType;
    private final String vehicleNumber;
   
    private final int wheels;

    public Bike( ) {
        this.vehicleType = VehicleType.BIKE;
        this.vehicleNumber =VehcileInterface.generate();
        this.wheels = 2;
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
