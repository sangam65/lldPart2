package elevatorSystem.elevatorStrategy;

import java.util.List;

import elevatorSystem.entites.Elevator;
import elevatorSystem.entites.Floor;

public interface ElevatorStrategy {
     Elevator findElevatorForFloor(Floor floor,List<Elevator>elevatorList);
     
}
