package inCompleteLib.models;

import java.util.List;
import java.util.Objects;

public class Book {
    private String bookId;
    private String bookCopyId;
    private String title;
    private List<String> authors;
    private List<String> publishers;
    private BorrowerMetaData borrowerMetaData;

    public class BorrowerMetaData {
        private String by;
        private boolean isBorrowed;
        private String dueDate;

        public BorrowerMetaData(String by, boolean isBorrowed, String dueDate) {
            this.by = by;
            this.isBorrowed = isBorrowed;
            this.dueDate = dueDate;
        }

        public String getBy() {
            return by;
        }

        public void setBy(String by) {
            this.by = by;
        }

        public boolean isBorrowed() {
            return isBorrowed;
        }

        public void setBorrowed(boolean borrowed) {
            isBorrowed = borrowed;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }
    }

    public Book(String bookId, String bookCopyId, String title, List<String> authors, List<String> publishers) {
        this.bookId = bookId;
        this.bookCopyId = bookCopyId;
        this.title = title;
        this.authors = authors;
        this.publishers = publishers;
        this.borrowerMetaData = new BorrowerMetaData("", false, "");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookCopyId.equals(book.bookCopyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookCopyId);
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(String bookCopyId) {
        this.bookCopyId = bookCopyId;
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

    public BorrowerMetaData getBorrowerMetaData() {
        return borrowerMetaData;
    }

    public void setBorrowerMetaData(BorrowerMetaData borrowerMetaData) {
        this.borrowerMetaData = borrowerMetaData;
    }
}
