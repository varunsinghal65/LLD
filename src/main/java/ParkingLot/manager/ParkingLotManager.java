package ParkingLot.manager;

import ParkingLot.exceptions.ParkingLotFullException;
import ParkingLot.models.*;
import ParkingLot.services.IParkingLotService;
import ParkingLot.services.ParkingLotServiceImpl;
import ParkingLot.services.SlotSelectionCriteria;

import java.util.*;

public class ParkingLotManager {
    private ParkingLot parkingLot;
    private final Set<String> parkingTickets;
    private final IParkingLotService parkingLotService;

    //setters and getters
    public ParkingLotManager() {
        this.parkingLot = null;
        this.parkingTickets = new HashSet<>();
        this.parkingLotService = new ParkingLotServiceImpl();
    }

    //use cases
    public void createParkingLot(String id, int noFloors, int slotsPerFloor) {
        List<ParkingLotFloor> floors = new ArrayList<>();
        for (int i = 0; i < noFloors; i++) {
            List<ParkingSlot> slots = new ArrayList<>();
            for (int j = 0; j < slotsPerFloor; j++) {
                ParkingSlot slot = new ParkingSlot();
                if (j == 0) {
                    slot.setType(VehicleType.TRUCK);
                } else if (j == 1 || j == 2) {
                    slot.setType(VehicleType.BIKE);
                } else {
                    slot.setType(VehicleType.CAR);
                }
                slots.add(slot);
            }
            ParkingLotFloor floor = new ParkingLotFloor(slots);
            floors.add(floor);
        }
        this.parkingLot = new ParkingLot(id, floors);
    }

    public Map<Integer, Integer> getFreeCount(VehicleType vehicleType) {
        return this.parkingLotService.getFreeSlotsCount(vehicleType, parkingLot);
    }

    public Map<Integer, List<Integer>> getFreeSlots(VehicleType vehicleType) {
        SlotSelectionCriteria criteria = (slot, inputVehicleType) -> slot.getType().equals(inputVehicleType) &&
                slot.getParkedVehicle() == null;
        return this.parkingLotService.getSlotsBasedOnCriteria(vehicleType, parkingLot, criteria);
    }

    public Map<Integer, List<Integer>> getOccupiedSlots(VehicleType vehicleType) {
        SlotSelectionCriteria criteria = (slot, inputVehicleType) -> slot.getType().equals(inputVehicleType) &&
                slot.getParkedVehicle() != null;
        return this.parkingLotService.getSlotsBasedOnCriteria(vehicleType, parkingLot, criteria);
    }

    public Vehicle unPark(String ticket) {
        if (!parkingTickets.contains(ticket)) {
            throw new IllegalArgumentException("Invalid ticket");
        }
        String[] args = ticket.split("_");
        String pid = args[0];
        int floorNo = Integer.parseInt(args[1]);
        int slotNo = Integer.parseInt(args[2]);
        if (!parkingLot.getId().equals(pid) ||
                parkingLot.getFloors().size() - floorNo < 0 ||
                parkingLot.getFloors().get(0).getSlots().size() - slotNo < 0 ||
                parkingLot.getFloors().get(floorNo - 1).getSlots().get(slotNo - 1) == null) {
            throw new IllegalArgumentException("Invalid ticket");
        }
        parkingTickets.remove(ticket);
        return this.parkingLotService.unPark(floorNo, slotNo, parkingLot);
    }

    public String park(String regNo, VehicleType type, String color) throws ParkingLotFullException {
        String ticket;
        if (type == null) {
            throw new IllegalArgumentException("Bad vehicle type received");
        }
        ticket = this.parkingLotService.park(parkingLot, new Vehicle(type, color, regNo));
        parkingTickets.add(ticket);
        return ticket;
    }
}
