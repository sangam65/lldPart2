package elevatorSystem.entites;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import elevatorSystem.elevatorStrategy.ElevatorStrategy;
import elevatorSystem.exception.BuildingException;
import elevatorSystem.exception.FloorException;

public class Building {
    private Map<Integer, Floor> floorList;
    private Map<Character, Elevator> elevatorList;
    private ElevatorStrategy elevatorStrategy;

    public ElevatorStrategy getElevatorStrategy() {
        return elevatorStrategy;
    }

    public void setElevatorStrategy(ElevatorStrategy elevatorStrategy) {
        this.elevatorStrategy = elevatorStrategy;
    }

    public Building() {
        this.elevatorList = new ConcurrentHashMap<>();
        this.floorList = new ConcurrentHashMap<>();
    }

    public  Floor addFloor(int floorNumber) throws FloorException{
        if(floorList.containsKey(floorNumber)){
            throw new FloorException("Floor is already added");
        }
        Floor floor=new Floor(floorNumber, this);
        this.floorList.put(floorNumber,floor);
        return floor;
    }
   
    public Elevator addElevator(char elevatorId)throws BuildingException {
        if(elevatorList.size()>6){
            throw new BuildingException("Building can't have more than 6 elevators");
        }
        if(elevatorList.containsKey(elevatorId)){
             throw new BuildingException("Elevator with given Id already present");
        }
        Floor floor=floorList.get(0);
        if(floor==null){
            throw new FloorException("Floor zero not present");
        }
        return new Elevator(floor, elevatorId, this);
    }
    private List<Elevator> getElevatorList(){
        return elevatorList.values().stream().collect(Collectors.toList());
    }
    public synchronized Elevator getElevator(Floor floor){
        Elevator elevator= this.elevatorStrategy.findElevatorForFloor(floor, getElevatorList());
        elevator.addFloor(floor);
        return elevator;

    }

}