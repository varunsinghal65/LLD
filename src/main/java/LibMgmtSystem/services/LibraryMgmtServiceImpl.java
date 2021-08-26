package LibMgmtSystem.services;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import LibMgmtSystem.exceptions.CopyIdNotFound;
import LibMgmtSystem.exceptions.IdNotFound;
import LibMgmtSystem.exceptions.RackNotAvailable;
import LibMgmtSystem.models.Book;
import LibMgmtSystem.models.Library;
import LibMgmtSystem.models.Rack;

public class LibraryMgmtServiceImpl implements ILibraryMgmtService {

	@Override
	public int addBook(Library library, Book book) throws RackNotAvailable {
		for (int i = 0; i < library.getRacks().size(); i++) {
			Rack currentRack = library.getRacks().get(i);
			if (currentRack.getBook() == null) {
				currentRack.setBook(book);
				return i + 1;
			}
		}
		throw new RackNotAvailable();
	}

	@Override
	public int removeBookByCopyId(Library library, String copyIdOfBookToRemove) throws CopyIdNotFound {
		for (int i = 0; i < library.getRacks().size(); i++) {
			Rack currentRack = library.getRacks().get(i);
			if (currentRack.getBook() != null && currentRack.getBook().getCopyId().equals(copyIdOfBookToRemove)) {
				currentRack.setBook(null);
				return i + 1;
			}
		}
		throw new CopyIdNotFound();
	}

	@Override
	public int removeBookByIdFirstCopy(Library library, String idOfBookToRemove) throws IdNotFound {
		for (int i = 0; i < library.getRacks().size(); i++) {
			Rack currentRack = library.getRacks().get(i);
			if (currentRack.getBook() != null && currentRack.getBook().getId().equals(idOfBookToRemove)) {
				currentRack.setBook(null);
				return i + 1;
			}
		}
		throw new IdNotFound();
	}

	@Override
	public Book getBookByIdFirstCopy(Library library, String bookId) {
		for (int i = 0; i < library.getRacks().size(); i++) {
			Rack currentRack = library.getRacks().get(i);
			if (currentRack.getBook() != null && currentRack.getBook().getId().equals(bookId)) {
				return currentRack.getBook();
			}
		}
		return null;
	}

	@Override
	public Book getBookByCopyIdFirstCopy(Library library, String copyBookId) {
		for (int i = 0; i < library.getRacks().size(); i++) {
			Rack currentRack = library.getRacks().get(i);
			if (currentRack.getBook() != null && currentRack.getBook().getCopyId().equals(copyBookId)) {
				return currentRack.getBook();
			}
		}
		return null;
	}

	@Override
	public Map<Integer, Book> searchBooksInLibrary(Library library, SearchCriteria searchCriteria,
			String attributeValue) {
		LinkedHashMap<Integer, Book> toReturn = new LinkedHashMap<>();
		for (int i = 0; i < library.getRacks().size(); i++) {
			Rack currentRack = library.getRacks().get(i);
			if (currentRack.getBook() != null 
					&& searchCriteria.isMatch(currentRack.getBook(), attributeValue)) {
				toReturn.put(i + 1, currentRack.getBook());
			}
		}
		return toReturn;
	}

	@Override
	public Set<Book> searchBooksInBorrowedRecord(Map<String, Set<Book>> borrowedRecord, SearchCriteria searchCriteria,
			String attributeValue) {
		Set<Book> toReturn = new HashSet<>();
		for (Set<Book> borrowedBooks : borrowedRecord.values()) {
			for (Book book : borrowedBooks) {
				if (searchCriteria.isMatch(book, attributeValue)) {
					toReturn.add(book);
				}
			}
		}
		return toReturn;
	}

}
