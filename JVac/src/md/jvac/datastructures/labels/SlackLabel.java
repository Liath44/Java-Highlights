package md.jvac.datastructures.labels;

import static java.text.MessageFormat.format;

public class SlackLabel extends SimplexLabel {
	private final static String TO_STRING_FORMAT = "S{0, number, integer}";

	public SlackLabel(int label) {
		super(getSlackMarker(), label);
	}

	@Override
	public String toString() {
		return format(TO_STRING_FORMAT, pharmacyID);
	}
}
