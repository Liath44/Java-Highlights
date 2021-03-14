package md.jvac.exceptions;

public class NoInputFileException extends Exception {
	private final static String MESSAGE = "Programme has been called without arguments. One argument required (path to input file)";

	@Override
	public String getMessage() {
		return MESSAGE;
	}
}
