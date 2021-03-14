package md.jvac.exceptions;

import static java.text.MessageFormat.format;

public class ImproperInputIndexingException extends Exception {
	private final static String MESSAGE_FORMAT = "Encountered improper index in line {0, number, integer} of {1} section.\nExpected index: {2, number, integer}\nProvided index: \"{3}\"";
	private final int lineIndex;
	private final String sectionName;
	private final String providedIndex;

	public ImproperInputIndexingException(int lineIndex, String sectionName, String providedIndex) {
		this.lineIndex = lineIndex;
		this.sectionName = sectionName;
		this.providedIndex = providedIndex;
	}

	@Override
	public String getMessage() {
		return format(MESSAGE_FORMAT, lineIndex + 1, sectionName, lineIndex, providedIndex);
	}
}
