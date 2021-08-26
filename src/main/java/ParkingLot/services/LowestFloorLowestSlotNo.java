package ParkingLot.services;

import ParkingLot.exceptions.ParkingLotFullException;
import ParkingLot.models.ParkingLot;
import ParkingLot.models.ParkingSlot;
import ParkingLot.models.Vehicle;

import java.util.List;

public class LowestFloorLowestSlotNo implements IParkingStrategy {
    @Override
    public String park(ParkingLot parkingLot, Vehicle vehicle) throws ParkingLotFullException {
        for (int i = 0; i < parkingLot.getFloors().size(); i++) {
            List<ParkingSlot> slots = parkingLot.getFloors().get(i).getSlots();
            for (int j = 0; j < slots.size(); j++) {
                ParkingSlot slot = slots.get(j);
                if (vehicle.getType().equals(slot.getType()) && slot.getParkedVehicle() == null) {
                    slot.setParkedVehicle(vehicle);
                    int floorNo = i + 1;
                    int slotNo = j + 1;
                    return parkingLot.getId() + "_" + floorNo + "_" + slotNo;
                }
            }
        }
        throw new ParkingLotFullException();
    }
}
