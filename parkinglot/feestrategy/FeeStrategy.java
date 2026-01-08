package parkinglot.feestrategy;


import parkinglot.entities.ParkingTicket;


public interface FeeStrategy {
    public long calculateFee(ParkingTicket parkingTicket);
}
