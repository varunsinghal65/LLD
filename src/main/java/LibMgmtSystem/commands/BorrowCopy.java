package LibMgmtSystem.commands;

import LibMgmtSystem.exceptions.CopyIdNotFound;
import LibMgmtSystem.manager.UseCaseManager;

public class BorrowCopy extends CommandExecutor {

	public BorrowCopy(UseCaseManager ucManager, String[] inputArgs) {
		super(ucManager, inputArgs);
	}

	@Override
	public void execute() {
		String copyId = inputArgs[1];
		String userId = inputArgs[2];
		String dueDate = inputArgs[3];
		try {
			int rackNo = ucManager.borrowBookByCopyId(userId, copyId, dueDate);
			System.out.print("Borrowed Book from rack: " + rackNo);
		} catch (CopyIdNotFound e) {
			System.out.print("Not Available.");
		}
	}

}
