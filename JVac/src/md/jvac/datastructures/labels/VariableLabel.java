package md.jvac.datastructures.labels;

import static java.text.MessageFormat.format;

public class VariableLabel extends SimplexLabel {
	private final static String TO_STRING_FORMAT = "{0, number, integer}->{1, number, integer}";

	public VariableLabel(int producentID, int pharmacyID) {
		super(producentID, pharmacyID);
	}

	@Override
	public String toString() {
		return format(TO_STRING_FORMAT, producentID, pharmacyID);
	}
}
