package ParkingLot.services;

import ParkingLot.exceptions.ParkingLotFullException;
import ParkingLot.models.ParkingLot;
import ParkingLot.models.ParkingSlot;
import ParkingLot.models.Vehicle;
import ParkingLot.models.VehicleType;

import java.util.List;
import java.util.Map;

public interface IParkingLotService {

    String park(IParkingStrategy parkingStrategy, ParkingLot parkingLot,
                Vehicle vehicle) throws ParkingLotFullException;

    Vehicle unPark(int floorNo, int slotNo, ParkingLot parkingLot);

    Map<Integer, Integer> getFreeSlotsCount(VehicleType vehicleType, ParkingLot parkingLot);

    Map<Integer, List<Integer>> getSlotsBasedOnCriteria(VehicleType vehicleType, ParkingLot parkingLot,
                                                        SlotSelectionCriteria criteria);
}
