package md.jvac.io;

import md.jvac.datastructures.iodata.TransactionData;
import md.jvac.exceptions.ConnectionNotPresentException;
import md.jvac.exceptions.MaxTransactionSubjectExceededException;

import static md.jvac.io.MessageCenter.logMessage;

public class TransactionDataVerifier {
	private final static String BEGIN_MESSAGE = "Final data verification";
	private final static int MAX_TRANSACTION_SUBJECTS = 1000;

	private TransactionDataVerifier() {
	}

	public static void verifyTransactionData(TransactionData transactionData) throws MaxTransactionSubjectExceededException, ConnectionNotPresentException {
		logMessage(BEGIN_MESSAGE);
		if (transactionData.getPharmaciesNumber() > MAX_TRANSACTION_SUBJECTS || transactionData.getProducentsNumber() > MAX_TRANSACTION_SUBJECTS) {
			throw new MaxTransactionSubjectExceededException(MAX_TRANSACTION_SUBJECTS);
		}
		transactionData.verifyIfEachConnectionExists();
	}
}
