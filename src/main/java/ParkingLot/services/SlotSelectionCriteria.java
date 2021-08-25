package ParkingLot.services;

import ParkingLot.models.ParkingSlot;
import ParkingLot.models.VehicleType;

@FunctionalInterface
public interface SlotSelectionCriteria {
    boolean isMatchingSlot(ParkingSlot slot, VehicleType queryType);
}