package md.jvac.exceptions;

public class ContradictoryDataException extends Exception {
	private final static String MESSAGE = "Provided data is contradictory. No plan could be devised.";

	@Override
	public String getMessage() {
		return MESSAGE;
	}
}
