package md.jvac.io;

import md.jvac.datastructures.iodata.Connection;
import md.jvac.datastructures.iodata.OutcomeNode;
import md.jvac.datastructures.iodata.TransactionData;
import md.jvac.datastructures.labels.SimplexLabel;
import md.jvac.datastructures.matrices.SimplexTableau;
import md.jvac.rationalnumber.RationalNumber;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static java.text.MessageFormat.format;
import static md.jvac.io.MessageCenter.logMessage;

public class OutputFileWriter {
	private final static String BEGIN_MESSAGE = "Writing output file";
	private final static String OUTCOME_LINE_FORMAT = "{0} -> {1} [Koszt = {2} * {3} = {4} PLN]";
	private final static String TOTAL_COST_FORMAT = "Opłaty całkowite: {0} PLN";
	private final static String OUTPUT_FILE_NAME = "OUTPUT";

	public static void writeOutput(SimplexTableau tableau, TransactionData transactionData) throws IOException {
		logMessage(BEGIN_MESSAGE);
		RationalNumber sum = new RationalNumber(0);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME))) {
			for (OutcomeNode outcomeNode : tableau.getOutcome()) {
				SimplexLabel label = outcomeNode.getLabel();
				int producentID = label.getProducentID();
				int pharmacyID = label.getPharmacyID();
				Connection connection = transactionData.getConnection(producentID, pharmacyID);
				String producent = transactionData.getProducent(producentID).getName();
				String pharmacy = transactionData.getPharmacy(pharmacyID).getName();
				RationalNumber vaccineCost = connection.getVaccineCost();
				RationalNumber vaccinesBought = outcomeNode.getVaccinesBought();
				RationalNumber outcome = vaccineCost.multiply(vaccinesBought);
				sum = sum.add(outcome);
				writer.write(format(OUTCOME_LINE_FORMAT, producent, pharmacy, vaccinesBought.toString(), vaccineCost.toString(), outcome.toString()));
				writer.write("\n");
			}
			writer.write(format(TOTAL_COST_FORMAT, sum.toString()));
		}
	}
}
