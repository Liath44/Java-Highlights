package md.jvac.exceptions;

import static java.text.MessageFormat.format;

public class ImproperCostFormatException extends Exception {
	private final static String MESSAGE_FORMAT = "Improperly formatted vaccine cost in line {0, number, integer} of section {2}.\nExpected decimal fraction with max. 2 decimal places (\".\" is the used separator)\nProvided value: \"{1}\"";
	private final int lineIndex;
	private final String providedValue;
	private final String sectionName;

	public ImproperCostFormatException(int lineIndex, String providedValue, String sectionName) {
		this.lineIndex = lineIndex + 1;
		this.providedValue = providedValue;
		this.sectionName = sectionName;
	}

	@Override
	public String getMessage() {
		return format(MESSAGE_FORMAT, lineIndex, providedValue, sectionName);
	}
}
