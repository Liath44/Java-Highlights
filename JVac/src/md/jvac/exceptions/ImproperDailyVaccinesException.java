package md.jvac.exceptions;

import java.text.MessageFormat;

public class ImproperDailyVaccinesException extends Exception {
	private final static String MESSAGE_FORMAT = "Provided amount of daily vaccines in index {0, number, integer} of section {1} is improper.\nExpected integer of value greater than 0.\nProvided: \"{2}\"";
	private final int lineIndex;
	private final String sectionName;
	private final String valueProvided;

	public ImproperDailyVaccinesException(int lineIndex, String sectionName, String valueProvided) {
		this.lineIndex = lineIndex;
		this.sectionName = sectionName;
		this.valueProvided = valueProvided;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format(MESSAGE_FORMAT, lineIndex, sectionName, valueProvided);
	}
}
