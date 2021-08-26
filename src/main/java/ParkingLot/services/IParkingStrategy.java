package ParkingLot.services;

import ParkingLot.exceptions.ParkingLotFullException;
import ParkingLot.models.ParkingLot;
import ParkingLot.models.Vehicle;

public interface IParkingStrategy {

    String park(ParkingLot parkingLot, Vehicle vehicle) throws ParkingLotFullException;

}
