package parkinglot.feestrategy;

import java.time.Duration;

import parkinglot.entities.ParkingTicket;

public class TimeBasedFeeStrategy implements FeeStrategy{

    @Override
    public long calculateFee(ParkingTicket parkingTicket) {
        Duration duration=Duration.between(parkingTicket.getExitTime(),parkingTicket.getParkingTime());
        long minutes=duration.toMinutes();
        
        return minutes*10;
    }

}
