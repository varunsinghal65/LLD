package LibMgmtSystem.commands;

import LibMgmtSystem.manager.UseCaseManager;

public class CommandExecutorFactory {

	public static CommandExecutor getInstance(String commandName, UseCaseManager ucManager, String[] inputArgs) {
		switch (commandName) {
		case "create_library":
			return new CreateLibrary(ucManager, inputArgs);
		case "add_book":
			return new AddBook(ucManager, inputArgs);
		case "remove_book_copy":
			return new RemoveBookCopy(ucManager, inputArgs);
		case "borrow_book":
			return new BorrowBook(ucManager, inputArgs);
		case "borrow_book_copy":
			return new BorrowCopy(ucManager, inputArgs);
		case "return_book_copy":
			return new ReturnBookCopy(ucManager, inputArgs);
		case "print_borrowed" :
			return new PrintBorrowed(ucManager, inputArgs);
		case "search" :
			return new Search(ucManager, inputArgs);
		default:
			throw new IllegalArgumentException("Command executor not found");
		}
	}
}
