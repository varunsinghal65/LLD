package LibMgmtSystem.commands;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import LibMgmtSystem.manager.UseCaseManager;
import LibMgmtSystem.models.Book;
import LibMgmtSystem.services.SearchCriteria;
import LibMgmtSystem.services.SearchCriteriaFactory;

public class Search extends CommandExecutor {

	public Search(UseCaseManager ucManager, String[] inputArgs) {
		super(ucManager, inputArgs);
	}

	@Override
	public void execute() {
		String attributeName = inputArgs[1];
		String attributeValue = inputArgs[2];
		SearchCriteria searchCriteria = SearchCriteriaFactory.getInstance(attributeName);
		// get books from library with the increasing rack number
		Map<Integer, Book> booksInLib = ucManager.getBooksInLibraryByAttribute(searchCriteria, attributeValue);
		// print
		for (Entry<Integer, Book> entry : booksInLib.entrySet()) {
			int rackNo = entry.getKey();
			Book book = entry.getValue();
			StringBuilder sbr = new StringBuilder("Book Copy: ");
			sbr.append(book.getCopyId() + " ");
			sbr.append(book.getId() + " ");
			sbr.append(book.getTitle() + " ");
			book.getAuthors().forEach(author -> sbr.append(author + ","));
			sbr.append(" ");
			book.getPublishers().forEach(author -> sbr.append(author + ","));
			sbr.append(" ");
			sbr.append(rackNo + " ");
			System.out.println(sbr);
		}
		// get books from borrowed record
		Set<Book> borrowedBooks = ucManager.getBooksInBorrowed(searchCriteria, attributeValue);
		// print
		for (Book book : borrowedBooks) {
			int rackNo = -1;
			StringBuilder sbr = new StringBuilder("Book Copy: ");
			sbr.append(book.getCopyId() + " ");
			sbr.append(book.getId() + " ");
			sbr.append(book.getTitle() + " ");
			book.getAuthors().forEach(author -> sbr.append(author + ","));
			sbr.append(" ");
			book.getPublishers().forEach(author -> sbr.append(author + ","));
			sbr.append(" ");
			sbr.append(rackNo + " ");
			sbr.append(book.getBorrowedBy());
			sbr.append(book.getDueDate());
			System.out.println(sbr);
		}
	}

}
