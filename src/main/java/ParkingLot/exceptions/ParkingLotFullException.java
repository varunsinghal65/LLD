package ParkingLot.exceptions;

public class ParkingLotFullException extends Exception {

    @Override
    public String getMessage() {
        return "Parking lot is full !!! :-(.";
    }
}
