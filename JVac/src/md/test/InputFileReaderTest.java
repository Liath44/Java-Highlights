package md.test;

import md.jvac.exceptions.DataDuplicationException;
import md.jvac.exceptions.ImproperAmountOfDataBitsException;
import md.jvac.exceptions.ImproperCostFormatException;
import md.jvac.exceptions.ImproperDailyVaccinesException;
import md.jvac.exceptions.ImproperInputIndexingException;
import md.jvac.exceptions.MissingSectionException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static java.lang.String.join;
import static md.jvac.io.InputFileReader.readInput;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class InputFileReaderTest {
	@Test
	public void ifProperInputIsReadThenNoExceptionsAreThrown() throws Exception {
		//given
		String properInputPath = createPath("src", "md", "test", "resources", "ProperInput");
		//when
		readInput(properInputPath);
		//then
		assertTrue(true);
	}

	private String createPath(String... files) {
		return join(File.separator, files);
	}

	@Test(expected = IOException.class)
	public void ifFileDoesNotExistThenExceptionIsThrown() throws Exception {
		//given
		String fileToNonExistingPath = createPath("src", "md", "test", "resources", "sfgdfgdsfgsgwsdr3q4564574nvwsxc");
		//when
		readInput(fileToNonExistingPath);
		//then
		fail();
	}

	@Test(expected = MissingSectionException.class)
	public void ifFileContainsNoProducentsHeaderThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithNoProducentsHeader = createPath("src", "md", "test", "resources", "NoProducentsHeader");
		//when
		readInput(pathToFileWithNoProducentsHeader);
		//then
		fail();
	}

	@Test(expected = ImproperInputIndexingException.class)
	public void ifFileContainsImproperIndexingForProducentsThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithImproperProducentsID = createPath("src", "md", "test", "resources", "ImproperProducentID");
		//when
		readInput(pathToFileWithImproperProducentsID);
		//then
		fail();
	}

	@Test(expected = MissingSectionException.class)
	public void ifFileContainsImproperProducentsHeaderThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithImproperProducentsHeader = createPath("src", "md", "test", "resources", "ImproperProducentHeader");
		//when
		readInput(pathToFileWithImproperProducentsHeader);
		//then
		fail();
	}

	@Test(expected = ImproperDailyVaccinesException.class)
	public void ifProducentContainsNegativeDailyVaccinesThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithImproperProducentsHeader = createPath("src", "md", "test", "resources", "ImproperProducentsDailyVaccines");
		//when
		readInput(pathToFileWithImproperProducentsHeader);
		//then
		fail();
	}

	@Test(expected = ImproperAmountOfDataBitsException.class)
	public void ifProducentLineMakesNoSenseThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithImproperProducentsHeader = createPath("src", "md", "test", "resources", "NonsenseProducentLine");
		//when
		readInput(pathToFileWithImproperProducentsHeader);
		//then
		fail();
	}

	@Test(expected = ImproperDailyVaccinesException.class)
	public void ifNumberOfDailyVaccinesIsNotANumberThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithImproperProducentsHeader = createPath("src", "md", "test", "resources", "ImproperProducentsDailyVaccines");
		//when
		readInput(pathToFileWithImproperProducentsHeader);
		//then
		fail();
	}

	@Test(expected = ImproperInputIndexingException.class)
	public void ifNoPharmaciesHeaderIsPresentThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithNoPharmaciesHeader = createPath("src", "md", "test", "resources", "NoPharmaciesHeader");
		//when
		readInput(pathToFileWithNoPharmaciesHeader);
		//then
		fail();
	}

	@Test(expected = ImproperAmountOfDataBitsException.class)
	public void ifPharmaciesHeaderIsImproperThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithImproperPharmaciesHeader = createPath("src", "md", "test", "resources", "ImproperPharmaciesHeader");
		//when
		readInput(pathToFileWithImproperPharmaciesHeader);
		//then
		fail();
	}

	@Test(expected = DataDuplicationException.class)
	public void ifFileContainsDuplicatedConnectionsThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithDuplicatedConnections = createPath("src", "md", "test", "resources", "ConnectionDuplication");
		//when
		readInput(pathToFileWithDuplicatedConnections);
		//then
		fail();
	}

	@Test(expected = DataDuplicationException.class)
	public void ifFileContainsDuplicatedProducentsThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithDuplicatedProducents = createPath("src", "md", "test", "resources", "ProducentDuplication");
		//when
		readInput(pathToFileWithDuplicatedProducents);
		//then
		fail();
	}

	@Test(expected = DataDuplicationException.class)
	public void ifFileContainsDuplicatedPharmaciesThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithDuplicatedPharmacies = createPath("src", "md", "test", "resources", "PharmaciesDuplication");
		//when
		readInput(pathToFileWithDuplicatedPharmacies);
		//then
		fail();
	}

	@Test(expected = ImproperCostFormatException.class)
	public void ifFileContainsVaccinesWithTooPreciseCostThenExceptionIsThrown() throws Exception {
		//given
		String pathToFileWithTooPreciseVaccineCost = createPath("src", "md", "test", "resources", "VaccineCostTooPrecise");
		//when
		readInput(pathToFileWithTooPreciseVaccineCost);
		//then
		fail();
	}
}
