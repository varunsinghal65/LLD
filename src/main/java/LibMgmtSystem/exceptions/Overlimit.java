package LibMgmtSystem.exceptions;

public class Overlimit extends Exception {

	@Override
	public String getMessage() {
		return "the user has breached the authorized limit.";
	}

}
