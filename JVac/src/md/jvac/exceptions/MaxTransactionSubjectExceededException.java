package md.jvac.exceptions;

import java.text.MessageFormat;

public class MaxTransactionSubjectExceededException extends Exception {
	private final static String MESSAGE_FORMAT = "Exceeded maximum amount of transaction subjects in one of the sections.\nMaximum amount: {0, number, integer}";
	private final int maxTransactionSubjects;

	public MaxTransactionSubjectExceededException(int maxTransactionSubjects) {
		this.maxTransactionSubjects = maxTransactionSubjects;
	}

	@Override
	public String getMessage() {
		return MessageFormat.format(MESSAGE_FORMAT, maxTransactionSubjects);
	}
}
