package models;

import java.util.LinkedList;
import java.util.Queue;

public class ReservationData {
    private static ReservationData instance;
    private LinkedList<String> availableSeats;
    private Queue<String> reservedSeats;

    private ReservationData() {
        // Initialize your data structures
        availableSeats = generateAvailableSeats();
        reservedSeats = new LinkedList<>();
    }

    // Define the generateAvailableSeats method within ReservationData
    private LinkedList<String> generateAvailableSeats() {
        LinkedList<String> seats = new LinkedList<>();
        for (int seatNumber = 1; seatNumber <= 53; seatNumber++) {
            seats.add("Seat " + seatNumber);
        }
        return seats;
    }

    public static ReservationData getInstance() {
        if (instance == null) {
            instance = new ReservationData();
        }
        return instance;
    }

    public LinkedList<String> getAvailableSeats() {
        return availableSeats;
    }

    public Queue<String> getReservedSeats() {
        return reservedSeats;
    }
}