package md.jvac.datastructures.labels;

public class SimplexLabel {
	private final static int ARTIFICIAL_MARKER = -2;
	private final static int SLACK_MARKER = -1;
	protected final int producentID;
	protected final int pharmacyID;

	public SimplexLabel(int producentID, int pharmacyID) {
		this.producentID = producentID;
		this.pharmacyID = pharmacyID;
	}

	public boolean equals(SimplexLabel target) {
		return target.getPharmacyID() == pharmacyID && target.getProducentID() == producentID;
	}

	public boolean isArtificial() {
		return producentID == getArtificialMarker();
	}

	public boolean isSlack() {
		return producentID == getSlackMarker();
	}

	public boolean isVariable() {
		return !(isSlack() || isArtificial());
	}

	public int getProducentID() {
		return producentID;
	}

	public int getPharmacyID() {
		return pharmacyID;
	}

	public static int getArtificialMarker() {
		return ARTIFICIAL_MARKER;
	}

	public static int getSlackMarker() {
		return SLACK_MARKER;
	}
}
