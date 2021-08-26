package LibMgmtSystem.services;

import java.util.List;
import java.util.Map;
import java.util.Set;

import LibMgmtSystem.exceptions.CopyIdNotFound;
import LibMgmtSystem.exceptions.IdNotFound;
import LibMgmtSystem.exceptions.RackNotAvailable;
import LibMgmtSystem.models.Book;
import LibMgmtSystem.models.Library;

public interface ILibraryMgmtService {

	int addBook(Library library, Book book) throws RackNotAvailable;

	int removeBookByCopyId(Library library, String copyId) throws CopyIdNotFound;

	int removeBookByIdFirstCopy(Library library, String bookId) throws IdNotFound;

	Book getBookByIdFirstCopy(Library library, String bookId);

	Book getBookByCopyIdFirstCopy(Library library, String copyBookId);

	Map<Integer, Book> searchBooksInLibrary(Library library, SearchCriteria searchCriteria, String attributeValue);

	Set<Book> searchBooksInBorrowedRecord(Map<String, Set<Book>> borrowedRecord, SearchCriteria searchCriteria,
			String attributeValue);

}
