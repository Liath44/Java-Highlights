package md.jvac.simplex;

import md.jvac.datastructures.iodata.Connection;
import md.jvac.datastructures.matrices.SimplexTableau;
import md.jvac.datastructures.iodata.TransactionData;
import md.jvac.datastructures.labels.ArtificialLabel;
import md.jvac.datastructures.labels.SimplexLabel;
import md.jvac.datastructures.labels.SlackLabel;
import md.jvac.datastructures.labels.VariableLabel;
import md.jvac.rationalnumber.RationalNumber;

import java.util.ArrayList;
import java.util.List;

import static md.jvac.io.MessageCenter.logMessage;

public class DataToTableauTransformer {
	private final static String BEGIN_MESSAGE = "Transforming data to simplex tableau";

	public DataToTableauTransformer() {
	}

	public SimplexTableau transformToTableau(TransactionData transactionData) {
		logMessage(BEGIN_MESSAGE);
		ListPair listPair = getFunctionListPair(transactionData);
		List<RationalNumber> functionCoefficients = listPair.getRationalNumberList();
		List<SimplexLabel> functionLabels = listPair.getStringList();
		listPair = getBasicListPair(transactionData);
		List<RationalNumber> basicCoefficients = listPair.getRationalNumberList();
		List<SimplexLabel> basicLabels = listPair.getStringList();
		SimplexTableau simplexTableau = createSimplexTableau(transactionData);
		simplexTableau.setBasicLabels(basicLabels);
		simplexTableau.setBasicCoefficients(basicCoefficients);
		simplexTableau.setFunctionLabels(functionLabels);
		simplexTableau.setFunctionCoefficients(functionCoefficients);
		simplexTableau.calculateRelativeProfit();
		return simplexTableau;
	}

	private ListPair getFunctionListPair(TransactionData transactionData) {
		List<RationalNumber> functionCoefficients = new ArrayList<>();
		List<SimplexLabel> functionLabels = new ArrayList<>();
		for (int pharmacyID = 0; pharmacyID < transactionData.getPharmaciesNumber(); pharmacyID++) {
			for (int producentID = 0; producentID < transactionData.getProducentsNumber(); producentID++) {
				Connection connection = transactionData.getConnection(producentID, pharmacyID);
				functionCoefficients.add(connection.getVaccineCost());
				functionLabels.add(new VariableLabel(producentID, pharmacyID));
			}
		}
		int nonVariableIndex = 0;
		for (int i = 0; i < transactionData.getProducentsNumber() + transactionData.getConnectionsNumber(); i++) {
			functionCoefficients.add(new RationalNumber(0));
			functionLabels.add(new SlackLabel(nonVariableIndex));
			nonVariableIndex++;
		}
		for (int j = 0; j < transactionData.getPharmaciesNumber(); j++) {
			functionCoefficients.add(new RationalNumber(0));
			functionLabels.add(new ArtificialLabel(nonVariableIndex));
			nonVariableIndex++;
		}
		return new ListPair(functionCoefficients, functionLabels);
	}

	private ListPair getBasicListPair(TransactionData transactionData) {
		int nonVariableIndex = 0;
		List<RationalNumber> basicCoefficients = new ArrayList<>();
		List<SimplexLabel> basicLabels = new ArrayList<>();
		for (int i = 0; i < transactionData.getProducentsNumber() + transactionData.getConnectionsNumber(); i++) {
			basicLabels.add(new SlackLabel(nonVariableIndex));
			basicCoefficients.add(new RationalNumber(0));
			nonVariableIndex++;
		}
		int artificialIndex = 0;
		for (int j = 0; j < transactionData.getPharmaciesNumber(); j++) {
			basicLabels.add(artificialIndex, new ArtificialLabel(nonVariableIndex));
			basicCoefficients.add(0, new RationalNumber(1));
			nonVariableIndex++;
			artificialIndex++;
		}
		return new ListPair(basicCoefficients, basicLabels);
	}

	private SimplexTableau createSimplexTableau(TransactionData transactionData) {
		int xSize = 2 * transactionData.getConnectionsNumber() + transactionData.getProducentsNumber() + transactionData.getPharmaciesNumber();
		int ySize = transactionData.getConnectionsNumber() + transactionData.getProducentsNumber() + transactionData.getPharmaciesNumber();
		SimplexTableau tableau = new SimplexTableau(xSize, ySize, new RationalNumber(0));
		setPharmaciesRequirements(transactionData, tableau);
		setProducentsRestrictions(transactionData, tableau);
		setConnectionsRestrictions(transactionData, tableau);
		addOutcomeColumn(transactionData, tableau);
		return tableau;
	}

	private void setPharmaciesRequirements(TransactionData transactionData, SimplexTableau tableau) {
		int artificialOffset = 2 * transactionData.getConnectionsNumber() + transactionData.getProducentsNumber();
		for (int y = 0; y < transactionData.getPharmaciesNumber(); y++) {
			for (int x = 0; x < transactionData.getProducentsNumber(); x++) {
				tableau.setElement(y * transactionData.getProducentsNumber() + x, y, new RationalNumber(1));
			}
			tableau.setElement(artificialOffset + y, y, new RationalNumber(1));
		}
	}

	private void setProducentsRestrictions(TransactionData transactionData, SimplexTableau tableau) {
		int slackOffset = transactionData.getConnectionsNumber();
		for (int y = 0; y < transactionData.getProducentsNumber(); y++) {
			for (int x = 0; x < transactionData.getPharmaciesNumber(); x++) {
				tableau.setElement(y + x * transactionData.getProducentsNumber(), y + transactionData.getPharmaciesNumber(), new RationalNumber(1));
			}
			tableau.setElement(slackOffset + y, y + transactionData.getPharmaciesNumber(), new RationalNumber(1));
		}
	}

	private void setConnectionsRestrictions(TransactionData transactionData, SimplexTableau tableau) {
		int slackOffset = transactionData.getConnectionsNumber() + transactionData.getProducentsNumber();
		int yOffset = transactionData.getPharmaciesNumber() + transactionData.getProducentsNumber();
		for (int i = 0; i < transactionData.getConnectionsNumber(); i++) {
			tableau.setElement(i, i + yOffset, new RationalNumber(1));
			tableau.setElement(slackOffset + i, i + yOffset, new RationalNumber(1));
		}
	}

	private void addOutcomeColumn(TransactionData transactionData, SimplexTableau tableau) {
		List<RationalNumber> outcomeColumn = new ArrayList<>();
		for (int i = 0; i < transactionData.getPharmaciesNumber(); i++) {
			outcomeColumn.add(new RationalNumber(transactionData.getPharmacy(i).getDailyVaccines()));
		}
		for (int j = 0; j < transactionData.getProducentsNumber(); j++) {
			outcomeColumn.add(new RationalNumber(transactionData.getProducent(j).getDailyVaccines()));
		}
		for (int producentID = 0; producentID < transactionData.getProducentsNumber(); producentID++) {
			for (int pharmacyID = 0; pharmacyID < transactionData.getPharmaciesNumber(); pharmacyID++) {
				outcomeColumn.add(new RationalNumber(transactionData.getConnection(producentID, pharmacyID).getDailyVaccines()));
			}
		}
		tableau.addColumn(outcomeColumn);
	}

	private static class ListPair {
		private final List<RationalNumber> rationalNumberList;
		private final List<SimplexLabel> stringList;

		public ListPair(List<RationalNumber> functionCoefficients, List<SimplexLabel> functionLabels) {
			this.rationalNumberList = functionCoefficients;
			this.stringList = functionLabels;
		}

		public List<RationalNumber> getRationalNumberList() {
			return rationalNumberList;
		}

		public List<SimplexLabel> getStringList() {
			return stringList;
		}
	}
}
