package LibMgmtSystem.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import LibMgmtSystem.manager.UseCaseManager;
import LibMgmtSystem.models.Book;

public class PrintBorrowed extends CommandExecutor {

	public PrintBorrowed(UseCaseManager ucManager, String[] inputArgs) {
		super(ucManager, inputArgs);
	}

	@Override
	public void execute() {
		String userId = inputArgs[1];
		Map<String, Set<Book>> borrowedRecord = ucManager.getBorrowRecord();
		if (borrowedRecord.containsKey(userId)) {
			List<Book> borrowedBooks = new ArrayList<>(borrowedRecord.get(userId));
			Comparator<Book> copyIdComparator = (Book book1, Book book2) -> {
				return book1.getCopyId().compareTo(book2.getCopyId());
			};
			Collections.sort(borrowedBooks, copyIdComparator);
			borrowedBooks.forEach(
					(Book book) -> System.out.println("Book Copy: " + book.getCopyId() + " " + book.getDueDate()));
		}
	}

}
