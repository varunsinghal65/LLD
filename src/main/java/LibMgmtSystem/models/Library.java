package LibMgmtSystem.models;

import java.util.List;

public class Library {

    private List<Rack> racks;
    public static final int BOOK_BORROW_LIMIT_PER_USER = 5;

    public Library(List<Rack> racks) {
        this.racks = racks;
    }

    public List<Rack> getRacks() {
        return racks;
    }

    public void setRacks(List<Rack> racks) {
        this.racks = racks;
    }
}
