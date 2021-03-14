package md.test;

import md.jvac.datastructures.iodata.Connection;
import md.jvac.datastructures.iodata.TransactionData;
import md.jvac.datastructures.iodata.TransactionSubject;
import md.jvac.exceptions.ConnectionNotPresentException;
import md.jvac.rationalnumber.RationalNumber;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TransactionDataTest {
	@Test
	public void ifAllConnectionsArePresentThenNoExceptionIsThrown() throws ConnectionNotPresentException {
		//given
		TransactionData transactionData = new TransactionData();
		setUpData(transactionData);
		//when
		transactionData.verifyIfEachConnectionExists();
		//then
		assertTrue(true);
	}

	private void setUpData(TransactionData transactionData) {
		setUpDataWithoutOneConnection(transactionData);
		addFinalConnection(transactionData);
	}

	private void setUpDataWithoutOneConnection(TransactionData transactionData) {
		transactionData.addPharmacy(new TransactionSubject("ph1", 2));
		transactionData.addPharmacy(new TransactionSubject("ph2", 3));
		transactionData.addProducent(new TransactionSubject("pr1", 2));
		transactionData.addProducent(new TransactionSubject("pr2", 3));
		transactionData.allocateSpaceForConnections();
		transactionData.addConnection(new Connection(0, 0, 1, new RationalNumber(1, 1)));
		transactionData.addConnection(new Connection(1, 0, 1, new RationalNumber(1, 1)));
		transactionData.addConnection(new Connection(0, 1, 1, new RationalNumber(1, 1)));
	}

	private void addFinalConnection(TransactionData transactionData) {
		transactionData.addConnection(new Connection(1, 1, 1, new RationalNumber(1, 1)));
	}

	@Test(expected = ConnectionNotPresentException.class)
	public void ifOneConnectionIsMissingThenExceptionIsThrown() throws ConnectionNotPresentException {
		//given
		TransactionData transactionData = new TransactionData();
		setUpDataWithoutOneConnection(transactionData);
		//when
		transactionData.verifyIfEachConnectionExists();
		//then
		fail();
	}

	@Test
	public void ifNumberOfConnectionsIsAskedForThenProperNumberIsGiven() {
		//given
		TransactionData transactionData = new TransactionData();
		setUpData(transactionData);
		//when
		int numberOfConnections = transactionData.getConnectionsNumber();
		//then
		assertEquals(numberOfConnections, 4);
	}
}
