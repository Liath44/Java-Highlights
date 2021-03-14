package md.jvac.datastructures.iodata;

import md.jvac.datastructures.labels.SimplexLabel;
import md.jvac.rationalnumber.RationalNumber;

public class OutcomeNode {
	private final SimplexLabel label;
	private final RationalNumber vaccinesBought;

	public OutcomeNode(SimplexLabel label, RationalNumber vaccinesBought) {
		this.label = label;
		this.vaccinesBought = vaccinesBought;
	}

	public SimplexLabel getLabel() {
		return label;
	}

	public RationalNumber getVaccinesBought() {
		return vaccinesBought;
	}
}
