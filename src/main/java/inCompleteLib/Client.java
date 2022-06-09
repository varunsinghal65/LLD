package inCompleteLib;

import inCompleteLib.Exceptions.*;
import inCompleteLib.models.AttributeEnum;
import inCompleteLib.models.Book;
import inCompleteLib.models.BookAttributePredicateFactory;
import inCompleteLib.services.LibraryServiceImpl;

import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;

public class Client {
    public static void main(String[] args) {
        final int borrowerLimit = 5;
        UcManager ucManager = new UcManager(borrowerLimit, new LibraryServiceImpl());
        while (true) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            String line = scanner.nextLine();
            String[] cmdArgs = line.split(" ");
            String command = cmdArgs[0];
            switch (command) {
                case "create_library":
                    ucManager.createLibrary(Integer.parseInt(cmdArgs[1]));
                    System.out.println("Library created with " + cmdArgs[1] + " racks");
                    break;
                case "add_book":
                    //create list of books based on copyIds
                    String[] copyIds = cmdArgs[5].split(",");
                    List<Book> booksToAdd = new ArrayList<>();
                    List<Integer> rackNumbers = new ArrayList<>();
                    for (String copyId : copyIds) {
                        booksToAdd.add(
                                new Book(
                                        cmdArgs[1],
                                        copyId,
                                        cmdArgs[2],
                                        Arrays.asList(cmdArgs[3].split(",")),
                                        Arrays.asList(cmdArgs[4].split(","))
                                )
                        );
                    }
                    //fire method to add books
                    try {
                        rackNumbers = ucManager.addBooks(booksToAdd);
                    } catch (RackNotAvailable e) {
                        System.out.println("Rack not available");
                        break;
                    }
                    //print statement if everything good
                    System.out.println("Added to racks: ");
                    rackNumbers.forEach(rackNum -> System.out.print(rackNum + ","));
                    break;
                case "remove_book_copy":
                    try {
                        int rackNo = ucManager.removeBookByCopyId(cmdArgs[1]);
                        System.out.println("Book was removed from rack: " + rackNo);
                    } catch (InvalidCopyIdException e) {
                        System.out.println("Invalid copy id.");
                    }
                    break;
                case "borrow_book":
                    try {
                        int rackNum = ucManager.borrowBookById(cmdArgs[0], cmdArgs[1], cmdArgs[2]);
                        System.out.println("Borrowed from rack : " + rackNum);
                    } catch (InvalidBookIdException e) {
                        System.out.println("Invalid book id.");
                    } catch (BookCopyNotAvailableException e) {
                        System.out.println("Book copy not available.");
                    } catch (OverlimitException e) {
                        System.out.println("Borrower limit reached.");
                    }
                    break;
                case "borrow_book_copy":
                    try {
                        int rackNum = ucManager.borrowBookByCopyId(cmdArgs[0], cmdArgs[1], cmdArgs[2]);
                        System.out.println("Borrowed from rack : " + rackNum);
                    } catch (InvalidCopyIdException e) {
                        System.out.println("Invalid book id.");
                    } catch (OverlimitException e) {
                        System.out.println("Borrower limit reached.");
                    }
                    break;
                case "return_book_copy":
                    try {
                        int rackNum = ucManager.returnBookByCopyId(cmdArgs[1]);
                        System.out.println("Returned book copy " + cmdArgs[1] + " to rack : " + rackNum);
                    } catch (InvalidCopyIdException e) {
                        System.out.println("Invalid copy id.");
                    }
                    break;
                case "print_borrowed":
                    ucManager.getBorrowedBooks().forEach(book -> System.out.println(book.getBookId()
                            + "," + book.getBorrowerMetaData().getDueDate()));
                    break;
                case "search":
                    //get borrowed books based on predicate, print them first, since in sorted order -1 comes first
                    //get racks in lib based on predicate, iterate all racks and print the book with the book number
                    break;
                default:
            }
        }
    }
}
