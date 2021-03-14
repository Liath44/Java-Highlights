package md.jvac.datastructures.iodata;

import md.jvac.datastructures.matrices.Matrix;
import md.jvac.exceptions.ConnectionNotPresentException;

import java.util.ArrayList;
import java.util.List;

public class TransactionData {
	private final static String TO_STRING_TITLE = "<><><> Transaction data <><><>";
	private final static String TO_STRING_PHARMACIES_TITLE = "Pharmacies";
	private final static String TO_STRING_PRODUCENTS_TITLE = "Producents";
	private final static String TO_STRING_CONNECTIONS_TITLE = "Connections";
	private final static String TAB = "  ";
	private final List<TransactionSubject> pharmacies;
	private final List<TransactionSubject> producents;
	private Matrix<Connection> connections;

	public TransactionData() {
		pharmacies = new ArrayList<>();
		producents = new ArrayList<>();
	}

	public void allocateSpaceForConnections() {
		connections = new Matrix<>(getProducentsNumber(), getPharmaciesNumber(), null);
	}

	public void addPharmacy(TransactionSubject pharmacy) {
		pharmacies.add(pharmacy);
	}

	public void addProducent(TransactionSubject producent) {
		producents.add(producent);
	}

	public void addConnection(Connection connection) {
		connections.setElement(connection.getProducentID(), connection.getPharmacyID(), connection);
	}

	public boolean pharmacyAlreadyExists(TransactionSubject pharmacyToAdd) {
		return pharmacies.contains(pharmacyToAdd);
	}

	public boolean producentAlreadyExists(TransactionSubject producentToAdd) {
		return producents.contains(producentToAdd);
	}

	public boolean connectionAlreadyExists(Connection connectionToAdd) {
		return connections.getElement(connectionToAdd.getProducentID(), connectionToAdd.getPharmacyID()) != null;
	}

	public void verifyIfEachConnectionExists() throws ConnectionNotPresentException {
		for (int x = 0; x < connections.getXSize(); x++) {
			for (int y = 0; y < connections.getYSize(); y++) {
				if (connections.getElement(x, y) == null) {
					throw new ConnectionNotPresentException(x, y);
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(TO_STRING_TITLE);
		builder.append("\n");
		appendBuilderWithList(builder, TO_STRING_PRODUCENTS_TITLE, producents);
		appendBuilderWithList(builder, TO_STRING_PHARMACIES_TITLE, pharmacies);
		appendBuilderWithConnections(builder);
		return builder.toString();
	}

	private void appendBuilderWithList(StringBuilder builder, String title, List<?> list) {
		builder.append(title);
		builder.append("\n");
		for (Object object : list) {
			builder.append(TAB);
			builder.append(object.toString());
			builder.append("\n");
		}
	}

	private void appendBuilderWithConnections(StringBuilder builder) {
		builder.append(TO_STRING_CONNECTIONS_TITLE);
		builder.append("\n");
		builder.append(connections.toString());
		builder.append("\n");
	}

	public int getPharmaciesNumber() {
		return pharmacies.size();
	}

	public int getProducentsNumber() {
		return producents.size();
	}

	public int getConnectionsNumber() {
		return connections.getXSize() * connections.getYSize();
	}

	public Connection getConnection(int producentID, int pharmacyID) {
		return connections.getElement(producentID, pharmacyID);
	}

	public TransactionSubject getPharmacy(int pharmacyID) {
		return pharmacies.get(pharmacyID);
	}

	public TransactionSubject getProducent(int producentID) {
		return producents.get(producentID);
	}
}
