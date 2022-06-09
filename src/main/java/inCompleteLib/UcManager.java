package inCompleteLib;

import inCompleteLib.Exceptions.*;
import inCompleteLib.models.Book;
import inCompleteLib.models.Library;
import inCompleteLib.models.Rack;
import inCompleteLib.services.ILibraryService;

import java.util.*;
import java.util.function.Predicate;

public class UcManager {
    private final Integer BORROWER_LIMIT;

    //state
    private final Library library;
    private final Set<Book> borrowedBooks;
    private final Map<String, Set<Book>> ledger;

    //business service
    private final ILibraryService libService;

    //constructor
    UcManager(int borrowLimit, ILibraryService libService) {
        this.BORROWER_LIMIT = borrowLimit;
        this.library = new Library(0, new ArrayList<>());
        this.libService = libService;
        this.borrowedBooks = new HashSet<>();
        this.ledger = new HashMap<>();
    }

    //use cases
    public void createLibrary(int noOfRacks) {
        for (int i = 0; i < noOfRacks; i++) {
            Rack rack = new Rack(i + 1, null);
            library.getRacks().add(rack);
        }
    }

    public List<Integer> addBooks(List<Book> booksToAdd) throws RackNotAvailable {
        if (booksToAdd.size() + library.getNumberOfRegisteredBooks() > library.getRacks().size()) {
            throw new RackNotAvailable();
        }
        List<Integer> rackNumbers = new ArrayList<>();
        booksToAdd.forEach((book) -> {
            rackNumbers.add(libService.addBook(book, library));
        });
        //increase the registered books count in the library
        library.setNumberOfRegisteredBooks(library.getNumberOfRegisteredBooks() + booksToAdd.size());
        return rackNumbers;
    }

    public int removeBookByCopyId(String copyId) throws InvalidCopyIdException {
        Predicate<Book> copyIdPredicate = (book) -> book != null && book.getBookCopyId().equals(copyId);
        int rackNum = libService.removeBookByPredicate(copyIdPredicate, library);
        //we ASSUME that a borrowed book cannot be removed
        //if user tries to remove a borrowed book OR a book that's not registered in the library,
        // we throw a InvalidCopyIdException;
        if (rackNum == -1) {
            throw new InvalidCopyIdException();
        }
        library.setNumberOfRegisteredBooks(library.getNumberOfRegisteredBooks() - 1);
        return rackNum;
    }

    public int borrowBookById(String bookId, String userId, String dueDate) throws OverlimitException,
            BookCopyNotAvailableException, InvalidBookIdException {
        //check if user reached his limit.
        if (ledger.containsKey(userId)
                && ledger.get(userId) != null
                && ledger.get(userId).size() == BORROWER_LIMIT) {
            throw new OverlimitException();
        }
        //remove the book based on book ID
        Predicate<Book> bookIdPredicate = book -> book != null && book.getBookId().equals(bookId);
        Rack toBeRemovedFrom = libService.removeAndGetRackByPredicate(bookIdPredicate, library);
        //if remove was fail -> 2 cases
        //case 1: book is not a registered book (never added to library)
        //case 2: book was borrowed and thus absent in library
        if (toBeRemovedFrom == null) {
            for (Book book : borrowedBooks) {
                if (book.getBookId().equals(bookId)) {
                    //case2
                    throw new BookCopyNotAvailableException();
                }
            }
            //case1
            throw new InvalidBookIdException();
        }
        //borrow was successful
        Book borrowedBook = toBeRemovedFrom.getBook();
        //set borrowed metadata
        borrowedBook.getBorrowerMetaData().setBorrowed(true);
        borrowedBook.getBorrowerMetaData().setDueDate(dueDate);
        borrowedBook.getBorrowerMetaData().setBy(userId);
        //update state
        ledger.putIfAbsent(userId, new HashSet<>());
        ledger.get(userId).add(borrowedBook);
        borrowedBooks.add(borrowedBook);
        return toBeRemovedFrom.getNumber();
    }

    public int borrowBookByCopyId(String copyId, String userId, String dueDate) throws OverlimitException, InvalidCopyIdException {
        //check if user reached his limit.
        if (ledger.containsKey(userId)
                && ledger.get(userId) != null
                && ledger.get(userId).size() == BORROWER_LIMIT) {
            throw new OverlimitException();
        }
        //remove book from library
        Predicate<Book> copyIdPredicate = book -> book != null && book.getBookCopyId().equals(copyId);
        Rack removedFrom = libService.removeAndGetRackByPredicate(copyIdPredicate, library);
        //removal was failure
        if (removedFrom == null) {
            throw new InvalidCopyIdException();
        }
        //borrow was success
        Book borrowedBook = removedFrom.getBook();
        //set the borrower metadata
        borrowedBook.getBorrowerMetaData().setBy(userId);
        borrowedBook.getBorrowerMetaData().setBorrowed(true);
        borrowedBook.getBorrowerMetaData().setDueDate(dueDate);
        //update state
        ledger.putIfAbsent(userId, new HashSet<>());
        ledger.get(userId).add(borrowedBook);
        borrowedBooks.add(borrowedBook);
        return removedFrom.getNumber();
    }

    public int returnBookByCopyId(String copyId) throws InvalidCopyIdException {
        Book toBeReturned = null;
        //find the borrowed book
        for (Book book: borrowedBooks) {
            if (book.getBookCopyId().equals(copyId)) {
                toBeReturned = book;
                break;
            }
        }
        //if not found
        if (toBeReturned == null) throw new InvalidCopyIdException();
        //if found reset the borrower metadata
        toBeReturned.getBorrowerMetaData().setDueDate("");
        toBeReturned.getBorrowerMetaData().setBorrowed(false);
        toBeReturned.getBorrowerMetaData().setBy("");
        //update state
        borrowedBooks.remove(toBeReturned);
        for (String userId: ledger.keySet()) {
            if (ledger.get(userId) != null
                    && ledger.get(userId).contains(toBeReturned)) {
                ledger.get(userId).remove(toBeReturned);
                break;
            }
        }
        return libService.addBook(toBeReturned, library);
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
