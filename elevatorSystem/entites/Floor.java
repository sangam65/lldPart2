package elevatorSystem.entites;

import elevatorSystem.exception.ElevatorException;
import elevatorSystem.exception.FloorException;

public class Floor {
    private final int floorNumber;
    private final Building building;

    public int getFloorNumber() {
        return floorNumber;
    }

    public Floor(int floorNumber,Building building)throws FloorException {
        if(building==null||floorNumber<0){
            throw new FloorException("invalid details of floor");
        }
        this.floorNumber = floorNumber;
        this.building=building;
    }
    public Elevator elevatorToGotToAnotherFloor(Floor floor) throws ElevatorException{
        if(this==floor){
            throw new ElevatorException("You are on same floor");

        }
        return this.building.getElevator(floor);

    }

    
}
