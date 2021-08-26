package LibMgmtSystem.commands;

import LibMgmtSystem.exceptions.CopyIdNotFound;
import LibMgmtSystem.manager.UseCaseManager;

public class RemoveBookCopy extends CommandExecutor {

	public RemoveBookCopy(UseCaseManager ucManager, String[] inputArgs) {
		super(ucManager, inputArgs);
	}

	@Override
	public void execute() {
		try {
			String copyId = inputArgs[1];
			int rackNo = ucManager.removeBookByCopyId(copyId);
			System.out.println("Removed book copy: " + copyId + " from rack number " + rackNo);
		} catch (CopyIdNotFound e) {
			System.out.println("Invalid copy ID.");
		}
	}

}
