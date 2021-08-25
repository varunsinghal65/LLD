package ParkingLot.models;

import java.util.List;

public class ParkingLotFloor {

    private List<ParkingSlot> slots;

    public ParkingLotFloor(List<ParkingSlot> slots) {
        this.slots = slots;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public void setSlots(List<ParkingSlot> slots) {
        this.slots = slots;
    }
}
