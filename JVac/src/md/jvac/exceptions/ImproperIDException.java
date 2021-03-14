package md.jvac.exceptions;

import java.text.MessageFormat;

public class ImproperIDException extends Exception {
	private final static String MESSAGE_FORMAT = "Line {0, number, integer} in last section contains improper index for {1}.\nExpected index from range <0;{2, number, integer}).\nFor index \"{3}\" there is no record in section {1}";
	private final int lineIndex;
	private final String typeOfIndex;
	private final int maxID;
	private final String providedIndex;

	public ImproperIDException(int lineIndex, String typeOfIndex, int maxID, String providedIndex) {
		this.lineIndex = lineIndex + 1;
		this.typeOfIndex = typeOfIndex;
		this.maxID = maxID;
		this.providedIndex = providedIndex;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format(MESSAGE_FORMAT, lineIndex, typeOfIndex, maxID, providedIndex);
	}
}
