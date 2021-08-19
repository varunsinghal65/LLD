package splitwise.models;

public class ExpenseMetaData {
    private String imageUrl;
    private String notes;
    private String name;

    @Override
    public String toString() {
        return "ExpenseMetaData{" +
                "imageUrl='" + imageUrl + '\'' +
                ", notes='" + notes + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
