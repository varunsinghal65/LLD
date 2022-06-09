package inCompleteLib.models;

public class Rack {
    private int number;
    private Book book;

    public Rack(int number, Book book) {
        this.number = number;
        this.book = book;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
