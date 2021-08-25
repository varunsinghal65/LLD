package ParkingLot;

import ParkingLot.exceptions.ParkingLotFullException;
import ParkingLot.manager.ParkingLotManager;
import ParkingLot.models.Vehicle;
import ParkingLot.models.VehicleType;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//Problem : https://workat.tech/machine-coding/practice/design-parking-lot-qm6hwq4wkhp8
public class ParkingLotClient {

    private static final ParkingLotManager parkingLotManager =
            new ParkingLotManager();

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String input = scanner.nextLine();
            String[] commands = input.split(" ");
            Arrays.stream(commands).forEach(command -> command = command.toLowerCase());
            switch (commands[0]) {
                case "create_parking_lot":
                    handleCreateParkingLot(commands);
                    break;
                case "display":
                    switch (commands[1]) {
                        case "free_count":
                            handleFreeCount(commands);
                            break;
                        case "free_slots":
                            handleFreeSlots(commands);
                            break;
                        case "occupied_slots":
                            handleOccupiedSlots(commands);
                            break;
                    }
                    break;
                case "park_vehicle":
                    handleParkVehicle(commands);
                    break;
                case "unpark_vehicle":
                    handleUnParkVehicle(commands);
                    break;
                case "exit":
                    System.exit(1);
                default:
                    System.out.println("Please enter a valid command.");
            }
        }
    }

    private static void handleUnParkVehicle(String[] commands) {
        String ticket = commands[1];
        try {
            Vehicle vehicle = parkingLotManager.unPark(ticket);
            System.out.println("Unparked vehicle with Registration Number: " + vehicle.getRegNumber() + " and Color: " + vehicle.getColor());
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid Ticket");
        }
    }

    private static void handleParkVehicle(String[] commands) {
        VehicleType type = VehicleType.valueOf(commands[1]);
        String regNo = commands[2];
        String color = commands[3];
        try {
            System.out.println("Parked vehicle. Ticket ID: " +
                    parkingLotManager.park(regNo, type, color));
        } catch (ParkingLotFullException exception) {
            System.out.println("Parking lot is full. Sorry. :-(");
        } catch (IllegalArgumentException exception) {
            System.out.println("Bad vehicle type received for parking.");
        }
    }

    private static void handleOccupiedSlots(String[] commands) {
        VehicleType type = VehicleType.valueOf(commands[2]);
        Map<Integer, List<Integer>> map = parkingLotManager.getOccupiedSlots(type);
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            StringBuilder sbr = new StringBuilder("Occupied slots for " + type + " in floor " + entry.getKey() + ": ");
            for (int slotNo : entry.getValue()) {
                sbr.append(slotNo);
                sbr.append(",");
            }
            System.out.println(sbr);
        }
    }

    private static void handleFreeSlots(String[] commands) {
        VehicleType type = VehicleType.valueOf(commands[2]);
        Map<Integer, List<Integer>> map = parkingLotManager.getFreeSlots(type);
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            StringBuilder sbr = new StringBuilder("Free slots  " + type + " in floor" + entry.getKey() + ": ");
            for (int slotNo : entry.getValue()) {
                sbr.append(slotNo);
                sbr.append(",");
            }
            System.out.println(sbr);
        }
    }

    private static void handleFreeCount(String[] commands) {
        VehicleType type = VehicleType.valueOf(commands[2]);
        Map<Integer, Integer> map = parkingLotManager.getFreeCount(type);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("No. of free slots for " + type + " on Floor " + entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void handleCreateParkingLot(String[] commands) {
        int totalFloors = Integer.parseInt(commands[2]);
        int totalSlotsPerFloor = Integer.parseInt(commands[3]);
        parkingLotManager.createParkingLot(commands[1], totalFloors, totalSlotsPerFloor);
        System.out.println("Created parking lot with " + totalFloors + " floors and " + totalSlotsPerFloor + " slots per floor.");
    }
}
