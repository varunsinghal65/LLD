package LibMgmtSystem.models;

import java.util.List;
import java.util.Objects;

public class Book {

    private String copyId;
    private String id; //same across multiple copies of this book
    private String title;
    private List<String> authors;
    private List<String> publishers;
    private String borrowedBy;
    private String dueDate;
    
	public Book(String copyId, String id, String title, List<String> authors, List<String> publishers, String borrowedBy) {
        this.copyId = copyId;
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publishers = publishers;
        this.borrowedBy = borrowedBy;
        this.dueDate = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(copyId, book.copyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyId);
    }

    public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
    
    public String getCopyId() {
        return copyId;
    }

    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<String> publishers) {
        this.publishers = publishers;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

}
