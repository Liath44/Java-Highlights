package md.jvac.exceptions;

import static java.text.MessageFormat.format;

public class ConnectionNotPresentException extends Exception {
	private final static String MESSAGE_FORMAT = "Connections must exist between each pharmacy and each producent.\nMissing connection: {0, number, integer} | {1, number, integer}";
	private final int producentID;
	private final int pharmacyID;

	public ConnectionNotPresentException(int producentID, int pharmacyID) {
		this.producentID = producentID;
		this.pharmacyID = pharmacyID;
	}

	@Override
	public String getMessage() {
		return format(MESSAGE_FORMAT, producentID, pharmacyID);
	}
}
