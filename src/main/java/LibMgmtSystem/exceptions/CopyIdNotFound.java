package LibMgmtSystem.exceptions;

public class CopyIdNotFound extends Exception {

	@Override
	public String getMessage() {
		return "CopyId not found in the library.";
	}
}
