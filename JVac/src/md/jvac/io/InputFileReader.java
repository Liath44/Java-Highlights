package md.jvac.io;

import md.jvac.datastructures.iodata.Connection;
import md.jvac.datastructures.iodata.TransactionData;
import md.jvac.datastructures.iodata.TransactionSubject;
import md.jvac.exceptions.DataDuplicationException;
import md.jvac.exceptions.ImproperAmountOfDataBitsException;
import md.jvac.exceptions.ImproperCostFormatException;
import md.jvac.exceptions.ImproperDailyVaccinesException;
import md.jvac.exceptions.ImproperIDException;
import md.jvac.exceptions.ImproperInputIndexingException;
import md.jvac.exceptions.MissingSectionException;
import md.jvac.rationalnumber.RationalNumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;
import static md.jvac.io.MessageCenter.logMessage;

public class InputFileReader {
	private final static String BEGIN_MESSAGE = "Reading and verifying input file";
	private final static InputSectionInfo PRODUCENTS_SECTION_INFO = new InputSectionInfo(1, "Producenci", "# Producenci szczepionek (id | nazwa | dzienna produkcja)", 3);
	private final static InputSectionInfo PHARMACIES_SECTION_INFO = new InputSectionInfo(2, "Apteki", "# Apteki (id | nazwa | dzienne zapotrzebowanie)", 3);
	private final static InputSectionInfo CONNECTIONS_SECTION_INFO = new InputSectionInfo(3, "Połączenia", "# Połączenia producentów i aptek (id producenta | id apteki | dzienna maksymalna liczba dostarczanych szczepionek | koszt szczepionki [zł] )", 4);
	private final static String DECIMAL_FRACTION_SEPARATOR = "\\.";
	private final static String END_OF_FILE_REACHED_MESSAGE = "<Reached end of file>";
	private final static String LINE_SPLITTING_REGEX = "\\|";

	private InputFileReader() {
	}

	public static TransactionData readInput(String path) throws IOException, MissingSectionException, ImproperAmountOfDataBitsException, ImproperInputIndexingException, ImproperDailyVaccinesException, ImproperIDException, ImproperCostFormatException, DataDuplicationException {
		logMessage(BEGIN_MESSAGE);
		TransactionData transactionData = new TransactionData();
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			readProducentsSection(reader, transactionData);
			readPharmaciesSection(reader, transactionData);
			transactionData.allocateSpaceForConnections();
			readConnectionsSection(reader, transactionData);
		}
		return transactionData;
	}

	private static void readProducentsSection(BufferedReader reader, TransactionData transactionData) throws IOException, MissingSectionException, ImproperAmountOfDataBitsException, ImproperInputIndexingException, ImproperDailyVaccinesException, DataDuplicationException {
		String line = reader.readLine();
		if (!line.equals(PRODUCENTS_SECTION_INFO.getSectionHeader())) {
			throwMissingSectionException(PRODUCENTS_SECTION_INFO, line);
		}
		line = reader.readLine();
		int lineIndex = 0;
		while (line != null && !line.equals(PHARMACIES_SECTION_INFO.getSectionHeader())) {
			String[] dataBits = line.split(LINE_SPLITTING_REGEX);
			TransactionSubject producent = getTransactionSubjectAndVerifyData(dataBits, lineIndex, PRODUCENTS_SECTION_INFO);
			if (transactionData.producentAlreadyExists(producent)) {
				throw new DataDuplicationException(lineIndex, PRODUCENTS_SECTION_INFO.getSectionName(), line);
			}
			transactionData.addProducent(producent);
			line = reader.readLine();
			lineIndex++;
		}
		if (line == null) {
			throwMissingSectionException(PHARMACIES_SECTION_INFO, END_OF_FILE_REACHED_MESSAGE);
		}
	}

	private static void throwMissingSectionException(InputSectionInfo sectionInfo, String inputLine) throws MissingSectionException {
		throw new MissingSectionException(sectionInfo.getSectionName(), inputLine, sectionInfo.getSectionHeader(), sectionInfo.getSectionIndex());
	}

	private static TransactionSubject getTransactionSubjectAndVerifyData(String[] dataBits, int lineIndex, InputSectionInfo sectionInfo) throws ImproperAmountOfDataBitsException, ImproperInputIndexingException, ImproperDailyVaccinesException {
		verifyDataBits(dataBits, sectionInfo, lineIndex);
		trimDataBits(dataBits);
		verifyIndex(dataBits[0], lineIndex, sectionInfo.getSectionName());
		int dailyVaccines = verifyAndGetDailyVaccines(dataBits[2], lineIndex, sectionInfo.getSectionName());
		return new TransactionSubject(dataBits[1], dailyVaccines);
	}

	private static void verifyDataBits(String[] dataBits, InputSectionInfo sectionInfo, int lineIndex) throws ImproperAmountOfDataBitsException {
		if (dataBits.length != sectionInfo.getNumberOfDataBits()) {
			throw new ImproperAmountOfDataBitsException(lineIndex + 1, sectionInfo.getSectionName(), dataBits.length, sectionInfo.getNumberOfDataBits());
		}
	}

	private static void trimDataBits(String[] dataBits) {
		for (int i = 0; i < dataBits.length; i++) {
			dataBits[i] = dataBits[i].trim();
		}
	}

	private static void verifyIndex(String providedIndex, int lineIndex, String sectionName) throws ImproperInputIndexingException {
		try {
			int index = parseInt(providedIndex);
			if (index != lineIndex) {
				throw new Exception();
			}
		} catch (Exception exception) {
			throw new ImproperInputIndexingException(lineIndex, sectionName, providedIndex);
		}
	}

	private static int verifyAndGetDailyVaccines(String providedValue, int lineIndex, String sectionName) throws ImproperDailyVaccinesException {
		try {
			int dailyVaccines = parseInt(providedValue);
			if (dailyVaccines < 0) {
				throw new Exception();
			}
			return dailyVaccines;
		} catch (Exception exception) {
			throw new ImproperDailyVaccinesException(lineIndex, sectionName, providedValue);
		}
	}

	private static void readPharmaciesSection(BufferedReader reader, TransactionData transactionData) throws IOException, ImproperDailyVaccinesException, ImproperInputIndexingException, ImproperAmountOfDataBitsException, MissingSectionException, DataDuplicationException {
		String line = reader.readLine();
		int lineIndex = 0;
		while (line != null && !line.equals(CONNECTIONS_SECTION_INFO.getSectionHeader())) {
			String[] dataBits = line.split(LINE_SPLITTING_REGEX);
			TransactionSubject pharmacy = getTransactionSubjectAndVerifyData(dataBits, lineIndex, PHARMACIES_SECTION_INFO);
			if (transactionData.pharmacyAlreadyExists(pharmacy)) {
				throw new DataDuplicationException(lineIndex, PHARMACIES_SECTION_INFO.getSectionName(), line);
			}
			transactionData.addPharmacy(pharmacy);
			line = reader.readLine();
			lineIndex++;
		}
		if (line == null) {
			throwMissingSectionException(CONNECTIONS_SECTION_INFO, END_OF_FILE_REACHED_MESSAGE);
		}
	}

	private static void readConnectionsSection(BufferedReader reader, TransactionData transactionData) throws IOException, ImproperDailyVaccinesException, ImproperAmountOfDataBitsException, ImproperIDException, ImproperCostFormatException, DataDuplicationException {
		String line = reader.readLine();
		int lineIndex = 0;
		while (line != null) {
			String[] dataBits = line.split(LINE_SPLITTING_REGEX);
			Connection connection = getConnectionAndVerifyData(dataBits, lineIndex, transactionData);
			if (transactionData.connectionAlreadyExists(connection)) {
				throw new DataDuplicationException(lineIndex, CONNECTIONS_SECTION_INFO.getSectionName(), line);
			}
			transactionData.addConnection(connection);
			line = reader.readLine();
			lineIndex++;
		}
	}

	private static Connection getConnectionAndVerifyData(String[] dataBits, int lineIndex, TransactionData transactionData) throws ImproperAmountOfDataBitsException, ImproperDailyVaccinesException, ImproperIDException, ImproperCostFormatException {
		verifyDataBits(dataBits, CONNECTIONS_SECTION_INFO, lineIndex);
		trimDataBits(dataBits);
		String sectionName = CONNECTIONS_SECTION_INFO.getSectionName();
		int producentID = verifyAndGetID(dataBits[0], lineIndex, transactionData.getProducentsNumber(), PRODUCENTS_SECTION_INFO.getSectionName());
		int pharmacyID = verifyAndGetID(dataBits[1], lineIndex, transactionData.getPharmaciesNumber(), PHARMACIES_SECTION_INFO.getSectionName());
		int dailyVaccines = verifyAndGetDailyVaccines(dataBits[2], lineIndex, sectionName);
		RationalNumber vaccineCost = verifyAndGetVaccineCost(dataBits[3], lineIndex, sectionName);
		return new Connection(producentID, pharmacyID, dailyVaccines, vaccineCost);
	}

	private static int verifyAndGetID(String subjectValue, int lineIndex, int listSize, String typeOfIndex) throws ImproperIDException {
		try {
			int id = parseInt(subjectValue);
			if (id < 0 || id >= listSize) {
				throw new Exception();
			}
			return id;
		} catch (Exception exception) {
			throw new ImproperIDException(lineIndex, typeOfIndex, listSize, subjectValue);
		}
	}

	private static RationalNumber verifyAndGetVaccineCost(String subjectValue, int lineIndex, String sectionName) throws ImproperCostFormatException {
		try {
			String[] dataBits = subjectValue.split(DECIMAL_FRACTION_SEPARATOR);
			switch (dataBits.length) {
				case 1:
					int integer = parseInt(dataBits[0]);
					if (integer < 0) {
						throw new Exception();
					}
					return new RationalNumber(integer);
				case 2:
					if (dataBits[1].length() == 1) {
						dataBits[1] = dataBits[1] + "0";
					}
					int integerPart = parseInt(dataBits[0]);
					int decimalPart = parseInt(dataBits[1]);
					if (integerPart < 0 || decimalPart < 0 || dataBits[1].length() > 2) {
						throw new Exception();
					}
					return decimalFractionToRationalNumber(integerPart, decimalPart);
				default:
					throw new Exception();
			}
		} catch (Exception exception) {
			throw new ImproperCostFormatException(lineIndex, subjectValue, sectionName);
		}
	}

	private static RationalNumber decimalFractionToRationalNumber(int integerPart, int decimalPart) {
		return new RationalNumber(100 * integerPart + decimalPart, 100);
	}
}
