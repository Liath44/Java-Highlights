package md.jvac.exceptions;

import java.text.MessageFormat;

public class MissingSectionException extends Exception {
	private final static String MESSAGE_FORMAT = "Missing section: {0}\nSubject section missing, misspelled or misplaced\nProvided section header: \"{1}\"\nCorrect section header: \"{2}\"\nIndex of improper section: {3, number, integer}";
	private final String subjectSection;
	private final String providedHeader;
	private final String correctHeader;
	private final int providedSectionIndex;

	public MissingSectionException(String subjectSection, String providedHeader, String correctHeader, int providedSectionIndex) {
		this.subjectSection = subjectSection;
		this.providedHeader = providedHeader;
		this.correctHeader = correctHeader;
		this.providedSectionIndex = providedSectionIndex;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format(MESSAGE_FORMAT, subjectSection, providedHeader, correctHeader, providedSectionIndex);
	}
}
