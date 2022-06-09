package inCompleteLib.models;

import java.util.List;

public class Library {

    private int numberOfRegisteredBooks;
    private List<Rack> racks;

    public Library(int numberOfRegisteredBooks, List<Rack> rack) {
        this.numberOfRegisteredBooks = numberOfRegisteredBooks;
        this.racks = rack;
    }

    public int getNumberOfRegisteredBooks() {
        return numberOfRegisteredBooks;
    }

    public void setNumberOfRegisteredBooks(int numberOfRegisteredBooks) {
        this.numberOfRegisteredBooks = numberOfRegisteredBooks;
    }

    public List<Rack> getRacks() {
        return racks;
    }

    public void setRacks(List<Rack> racks) {
        this.racks = racks;
    }
}
