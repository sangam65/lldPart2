package parkinglot.entities.vehicles;



import parkinglot.enums.VehicleType;

public class Bike  implements VehicleInterface{
    private final VehicleType vehicleType;
    private final String vehicleNumber;
   
    private final int wheels;

    public Bike(String vehicleNumber ) {
        this.vehicleType = VehicleType.BIKE;
        this.vehicleNumber =vehicleNumber;
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
