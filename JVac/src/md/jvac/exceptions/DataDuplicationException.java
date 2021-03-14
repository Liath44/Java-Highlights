package md.jvac.exceptions;

import java.text.MessageFormat;

public class DataDuplicationException extends Exception {
	private final static String MESSAGE_FORMAT = "Data duplicate in line {0, number, integer} of section {1}\nFound duplicate: \"{2}\"";
	private final int lineIndex;
	private final String sectionName;
	private final String dataLine;

	public DataDuplicationException(int lineIndex, String sectionName, String dataLine) {
		this.lineIndex = lineIndex + 1;
		this.sectionName = sectionName;
		this.dataLine = dataLine;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format(MESSAGE_FORMAT, lineIndex, sectionName, dataLine);
	}
}
