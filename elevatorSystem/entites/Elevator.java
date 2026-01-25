package elevatorSystem.entites;

import java.util.TreeSet;

import elevatorSystem.enums.LiftDirection;
import elevatorSystem.exception.ElevatorException;

public class Elevator {
    class Pair{
        private LiftDirection liftDirection;
        private  final Floor floor;
        public LiftDirection getLiftDirection() {
            return liftDirection;
        }
        public Floor getFloor() {
            return floor;
        }
        public Pair(LiftDirection liftDirection,Floor floor) {
            this.liftDirection = liftDirection;
            this.floor=floor;
        }
    }
    private Floor currentFloor;
    @SuppressWarnings("unused")
    private final Building building;
    private LiftDirection liftDirection;
    private final char elevatorId;
    public char getElevatorId() {
        return elevatorId;
    }
    public Floor getCurrentFloor() {
        return currentFloor;
    }
    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
    public LiftDirection getLiftDirection() {
        return liftDirection;
    }
    public void setLiftDirection(LiftDirection liftDirection) {
        this.liftDirection = liftDirection;
    }
    private TreeSet<Pair>floors;
    public TreeSet<Pair> getFloors() {
        return floors;
    }
    public void setFloors(TreeSet<Pair> floors) {
        this.floors = floors;
    }
    public Elevator(Floor floor,char elevatorId,Building building){
        if(building==null||floor==null){
            throw new ElevatorException("Invalid details to create elevator");
        }
        this.building=building;
        this.elevatorId=elevatorId;
        this.currentFloor=floor;
        this.liftDirection=LiftDirection.STILL;
        this.floors=new TreeSet<>((Pair a,Pair b)->{
            if(a.getLiftDirection().equals(b.getLiftDirection())){
                int floorA=a.getFloor().getFloorNumber();

                    int floorB=b.getFloor().getFloorNumber();
                if(a.getLiftDirection().equals(LiftDirection.UP)){
                    return Integer.compare(floorA,floorB);
                    
                }
                else{
                      return Integer.compare(floorB,floorA);
                }
            }
            return Integer.compare(1,2);
        });
    }
    public void addFloor(Floor floor){
        if(this.currentFloor==floor){
            throw new ElevatorException("Elevator is already at this floor");
        }
        if(this.liftDirection.equals(LiftDirection.STILL)){
            if(floor.getFloorNumber()>this.currentFloor.getFloorNumber()){
                this.floors.add(new Pair(LiftDirection.UP, floor));
            }
            else{
                this.floors.add(new Pair(LiftDirection.DOWN, floor));
            }
        }
        else{
            this.floors.add(new Pair(liftDirection, floor));
        }
    }
    public void elevatorMove()throws ElevatorException{
        if(this.floors.isEmpty()){
            throw new ElevatorException("No floors for lift");
        }
        Pair pair=this.floors.first();
        System.out.println(" lift at this floor" +pair.getFloor().getFloorNumber());
        this.currentFloor=pair.getFloor();
        this.floors.removeFirst();
        if(this.floors.isEmpty())
        {
            this.liftDirection=LiftDirection.STILL;
        }

    }

}
