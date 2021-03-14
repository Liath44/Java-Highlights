package md.jvac.exceptions;

import static java.text.MessageFormat.format;

public class InvalidColumnSizeException extends RuntimeException {
	private final static String MESSAGE_FORMAT = "Attempted to add a column to a matrix with too long/short size.\nColumn size: {0, integer, number}\nMatrix height: {1, integer, size}";
	private final int columnSize;
	private final int matrixHeight;

	public InvalidColumnSizeException(int columnSize, int matrixHeight) {
		this.columnSize = columnSize;
		this.matrixHeight = matrixHeight;
	}

	@Override
	public String getMessage() {
		return format(MESSAGE_FORMAT, columnSize, matrixHeight);
	}
}
