package LibMgmtSystem.exceptions;

public class RackNotAvailable extends Exception {
	@Override
	public String getMessage() {
		return "Rack not available";
	}
}
