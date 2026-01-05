package parkinglot.entities.vehciles;

import java.util.Random;

import parkinglot.enums.VehicleType;

public interface VehcileInterface {
    public VehicleType getVehcileType();

    public int getWheels();
    public String getVehcileNumber();
    public static final String[] STATES = {
        "MH","DL","KA","TN","UP","GJ","RJ","WB","HR","PB"
    };

    public static final Random RANDOM = new Random();

    public static String generate() {
        String state = STATES[RANDOM.nextInt(STATES.length)];
        int rto = RANDOM.nextInt(99) + 1; // 01–99

        char first = (char) ('A' + RANDOM.nextInt(26));
        char second = (char) ('A' + RANDOM.nextInt(26));

        int number = RANDOM.nextInt(10000); // 0000–9999

        return String.format("%s %02d %c%c %04d",
                state, rto, first, second, number);
    }
}
