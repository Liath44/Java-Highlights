import md.jvac.datastructures.iodata.TransactionData;
import md.jvac.datastructures.matrices.SimplexTableau;
import md.jvac.exceptions.NoInputFileException;
import md.jvac.io.MessageCenter;
import md.jvac.simplex.DataToTableauTransformer;
import md.jvac.simplex.SimplexSolver;

import static md.jvac.io.InputFileReader.readInput;
import static md.jvac.io.MessageCenter.logMessage;
import static md.jvac.io.OutputFileWriter.writeOutput;
import static md.jvac.io.TransactionDataVerifier.verifyTransactionData;

public class JVac {
	private final static String END_MESSAGE = "Done.";

	public static void main(String[] args) {
		try {
			if (args.length == 0) {
				throw new NoInputFileException();
			}
			TransactionData transactionData = readInput(args[0]);
			verifyTransactionData(transactionData);
			DataToTableauTransformer transformer = new DataToTableauTransformer();
			SimplexTableau tableau = transformer.transformToTableau(transactionData);
			SimplexSolver solver = new SimplexSolver();
			solver.performSimplex(tableau);
			writeOutput(tableau, transactionData);
			logMessage(END_MESSAGE);
		} catch (Exception exception) {
			MessageCenter.logException(exception);
		}
	}
}