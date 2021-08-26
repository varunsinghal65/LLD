package LibMgmtSystem.manager;

import LibMgmtSystem.exceptions.CopyIdNotFound;
import LibMgmtSystem.exceptions.IdNotFound;
import LibMgmtSystem.exceptions.Overlimit;
import LibMgmtSystem.exceptions.RackNotAvailable;
import LibMgmtSystem.models.Book;
import LibMgmtSystem.models.Library;
import LibMgmtSystem.models.Rack;
import LibMgmtSystem.services.ILibraryMgmtService;
import LibMgmtSystem.services.LibraryMgmtServiceImpl;
import LibMgmtSystem.services.SearchCriteria;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UseCaseManager {

	private Library library;
	private HashMap<String, Set<Book>> borrowRecord;

	private ILibraryMgmtService libraryMgmtService;

	public UseCaseManager() {
		library = null;
		borrowRecord = new HashMap<String, Set<Book>>();
		libraryMgmtService = new LibraryMgmtServiceImpl();
	}

	public void createLibrary(int noRacks) {
		List<Rack> racks = new ArrayList<>();
		for (int i = 0; i < noRacks; i++) {
			Rack rack = new Rack(null);
			racks.add(rack);
		}
		library = new Library(racks);
	}

	public List<Integer> addBook(Book book, List<String> copyIds) throws RackNotAvailable {
		List<Integer> rackNos = new ArrayList<Integer>();
		for (int i = 0; i < copyIds.size(); i++) {
			Book clonedBook = new Book(
					copyIds.get(i),
					book.getId(), 
					book.getTitle(),
					book.getAuthors(),
					book.getPublishers(),
					book.getBorrowedBy());
			int rackNo = libraryMgmtService.addBook(library, clonedBook);
			rackNos.add(rackNo);
		}
		return rackNos;
	}

	public int removeBookByCopyId(String copyId) throws CopyIdNotFound {
		return libraryMgmtService.removeBookByCopyId(library, copyId);
	}

	public int borrowBookById(String userId, String bookId, String dueDate)
			throws Overlimit, IdNotFound, CopyIdNotFound {
		// add user to borrowed record
		borrowRecord.putIfAbsent(userId, new HashSet<Book>());
		// check if user breached borrow limit
		if (borrowRecord.get(userId).size() == Library.BOOK_BORROW_LIMIT_PER_USER) {
			throw new Overlimit();
		}
		// get the book from library
		Book book = libraryMgmtService.getBookByIdFirstCopy(library, bookId);
		// if book not in library check if its borrowed by a user
		if (book == null && isBookIdInBorrowedRecord(bookId)) {
			throw new CopyIdNotFound();
		}
		// remove the book from the library
		int rackNo = libraryMgmtService.removeBookByIdFirstCopy(library, bookId);
		// set the book borrowedBy attribute and due date attribute
		book.setBorrowedBy(userId);
		book.setDueDate(dueDate);
		// add the book now to borrowRecord using user id.
		borrowRecord.get(userId).add(book);
		// return the rack no from where it was borrowed
		return rackNo;
	}

	public int borrowBookByCopyId(String userId, String copyId, String dueDate) throws CopyIdNotFound {
		// add users to borrow record if absent
		borrowRecord.put(userId, new HashSet<Book>());
		// get the book by copyId
		Book book = libraryMgmtService.getBookByCopyIdFirstCopy(library, copyId);
		// if book not present in library throw exception
		if (book == null)
			throw new CopyIdNotFound();
		// get the rack no and remove the book from library
		int rackNo = libraryMgmtService.removeBookByCopyId(library, copyId);
		// set the borrowed by and due date attibutes of the book
		book.setBorrowedBy(userId);
		book.setDueDate(dueDate);
		// insert in the borrow record
		borrowRecord.get(userId).add(book);
		// return the rack no
		return rackNo;
	}

	public int returnBookCopy(String copyId) throws CopyIdNotFound, RackNotAvailable {
		// check for the copyId in borrowed map
		// if not found, throw exception
		// if found, remove the book from borrowed record
		Book borrowedBook = removeBookByCopyIdFromBorrowedRecord(copyId);
		//reset the borrow attributes
		borrowedBook.setDueDate(null);
		borrowedBook.setBorrowedBy(null);
		// add it in the library and return rackno.
		return libraryMgmtService.addBook(library, borrowedBook);
	}
	
	public HashMap<String, Set<Book>> getBorrowRecord() {
		return borrowRecord;
	}

	public Map<Integer, Book> getBooksInLibraryByAttribute(SearchCriteria searchCriteria, String attributeValue) {
		return libraryMgmtService.searchBooksInLibrary(library, searchCriteria, attributeValue);
	}
	
	public Set<Book> getBooksInBorrowed(SearchCriteria searchCriteria, String attributeValue) {
		return libraryMgmtService.searchBooksInBorrowedRecord(borrowRecord, searchCriteria, attributeValue);
	}
	
	private boolean isBookIdInBorrowedRecord(String bookId) {
		for (Set<Book> borrowedBooks : borrowRecord.values()) {
			for (Book book : borrowedBooks) {
				if (book.getId().equals(bookId)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private Book removeBookByCopyIdFromBorrowedRecord(String copyId) throws CopyIdNotFound {
		Book toReturn = null;
		for (Set<Book> borrowedBooks : borrowRecord.values()) {
			for (Book book : borrowedBooks) {
				if (book.getCopyId().equals(copyId)) {
					toReturn = book;
					break;
				}
			}
			if (toReturn != null) {
				borrowedBooks.remove(toReturn);
				return toReturn;
			}
		}
		throw new CopyIdNotFound();
	}
}
