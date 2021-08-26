package ParkingLot.services;

import ParkingLot.exceptions.ParkingLotFullException;
import ParkingLot.models.ParkingLot;
import ParkingLot.models.ParkingSlot;
import ParkingLot.models.Vehicle;
import ParkingLot.models.VehicleType;

import java.util.*;

public class ParkingLotServiceImpl implements IParkingLotService {

    @Override
    public String park(IParkingStrategy parkingStrategy, ParkingLot parkingLot,
                       Vehicle vehicle) throws ParkingLotFullException {
        return parkingStrategy.park(parkingLot, vehicle);
    }

    @Override
    public Vehicle unPark(int floorNo, int slotNo, ParkingLot parkingLot) {
        Vehicle unParkedVehicle = parkingLot.getFloors().get(floorNo - 1).getSlots().get(slotNo - 1).getParkedVehicle();
        parkingLot.getFloors().get(floorNo - 1).getSlots().get(slotNo - 1).setParkedVehicle(null);
        return unParkedVehicle;
    }

    @Override
    public Map<Integer, Integer> getFreeSlotsCount(VehicleType vehicleType, ParkingLot parkingLot) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < parkingLot.getFloors().size(); i++) {
            int count = 0;
            List<ParkingSlot> slots = parkingLot.getFloors().get(i).getSlots();
            for (ParkingSlot slot : slots) {
                if (slot.getType().equals(vehicleType) && slot.getParkedVehicle() == null) {
                    count++;
                }
            }
            map.put(i + 1, count);
        }
        return map;
    }

    @Override
    public Map<Integer, List<Integer>> getSlotsBasedOnCriteria(VehicleType vehicleType,
                                                               ParkingLot parkingLot,
                                                               SlotSelectionCriteria criteria) {
        LinkedHashMap<Integer, List<Integer>> map = new LinkedHashMap<>();
        for (int i = 0; i < parkingLot.getFloors().size(); i++) {
            List<ParkingSlot> slots = parkingLot.getFloors().get(i).getSlots();
            map.put(i + 1, new ArrayList<>());
            for (int j = 0; j < slots.size(); j++) {
                ParkingSlot slot = slots.get(j);
                if (criteria.isMatchingSlot(slot, vehicleType)) {
                    map.get(i + 1).add(j + 1);
                }
            }
        }
        return map;
    }
}
