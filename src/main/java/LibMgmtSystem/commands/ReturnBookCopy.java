package LibMgmtSystem.commands;

import LibMgmtSystem.exceptions.CopyIdNotFound;
import LibMgmtSystem.exceptions.RackNotAvailable;
import LibMgmtSystem.manager.UseCaseManager;

public class ReturnBookCopy extends CommandExecutor {

	public ReturnBookCopy(UseCaseManager ucManager, String[] inputArgs) {
		super(ucManager, inputArgs);
	}

	@Override
	public void execute() {
		String copyId = inputArgs[1];
		try {
			int rackNo = ucManager.returnBookCopy(copyId);
			System.out.println("Returned book copy " + copyId + " and added to rack: " + rackNo);
		} catch (CopyIdNotFound e) {
			System.out.print("No such copyId books exists in the system.");
		} catch (RackNotAvailable e) {
			System.out.print("Rack not available.");
		}
	}

}
