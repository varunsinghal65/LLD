package LibMgmtSystem.commands;

import LibMgmtSystem.exceptions.CopyIdNotFound;
import LibMgmtSystem.exceptions.IdNotFound;
import LibMgmtSystem.exceptions.Overlimit;
import LibMgmtSystem.manager.UseCaseManager;

public class BorrowBook extends CommandExecutor {

	public BorrowBook(UseCaseManager ucManager, String[] inputArgs) {
		super(ucManager, inputArgs);
	}

	@Override
	public void execute() {
		String bookId = inputArgs[1];
		String userId = inputArgs[2];
		String dueDate = inputArgs[3];
		try {
			int rackNo = ucManager.borrowBookById(userId, bookId, dueDate);
			System.out.print("Borrowed Book from rack: " + rackNo);
		} catch (Overlimit e) {
			System.out.println("Overlimit.");
		} catch (IdNotFound e) {
			System.out.print("Invalid id.");
		} catch (CopyIdNotFound e) {
			System.out.print("Not Available.");
		}
	}

}
