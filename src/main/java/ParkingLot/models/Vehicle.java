package ParkingLot.models;

public class Vehicle {

    private VehicleType type;
    private String color;
    private String regNumber;

    public Vehicle(VehicleType type, String color, String regNumber) {
        this.type = type;
        this.color = color;
        this.regNumber = regNumber;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

}
