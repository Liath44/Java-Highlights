package md.jvac.exceptions;

import static java.text.MessageFormat.format;

public class ImproperAmountOfDataBitsException extends Exception {
	private final static String MESSAGE_FORMAT = "Line {0, number, integer} in section {1} contains improper amount of information pieces.\nAmount provided: {2, number, integer}\nAmount necessary: {3, number, integer}";
	private final int lineIndex;
	private final String sectionName;
	private final int amountOfBitsProvided;
	private final int amountOfBitsNecessary;

	public ImproperAmountOfDataBitsException(int lineIndex, String sectionName, int amountOfBitsProvided, int amountOfBitsNecessary) {
		this.lineIndex = lineIndex;
		this.sectionName = sectionName;
		this.amountOfBitsProvided = amountOfBitsProvided;
		this.amountOfBitsNecessary = amountOfBitsNecessary;
	}

	@Override
	public String getMessage() {
		return format(MESSAGE_FORMAT, lineIndex, sectionName, amountOfBitsProvided, amountOfBitsNecessary);
	}
}
