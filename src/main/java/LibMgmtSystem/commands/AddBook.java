package LibMgmtSystem.commands;

import java.util.Arrays;
import java.util.List;

import LibMgmtSystem.exceptions.RackNotAvailable;
import LibMgmtSystem.manager.UseCaseManager;
import LibMgmtSystem.models.Book;

public class AddBook extends CommandExecutor {

	public AddBook(UseCaseManager ucManager, String[] inputArgs) {
		super(ucManager, inputArgs);
	}

	@Override
	public void execute() {
		String bookId = inputArgs[1];
		String title = inputArgs[2];
		List<String> authors = Arrays.asList(inputArgs[3].split(","));
		List<String> publishers = Arrays.asList(inputArgs[4].split(","));
		List<String> copyIds = Arrays.asList(inputArgs[5].split(","));

		Book book = new Book(null, bookId, title, authors, publishers, null);
		try {
			List<Integer> addedRackNos = ucManager.addBook(book, copyIds);
			StringBuilder sbr = new StringBuilder("Added books to rack number : ");
			addedRackNos.forEach((rackNo)->{
				sbr.append(rackNo);
				sbr.append(",");
			});
			System.out.println(sbr);
		} catch (RackNotAvailable e) {
			System.out.println("Rack not available");
		}
	}

}
