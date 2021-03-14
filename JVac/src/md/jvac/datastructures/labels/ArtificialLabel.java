package md.jvac.datastructures.labels;

import static java.text.MessageFormat.format;

public class ArtificialLabel extends SimplexLabel {
	private final static String TO_STRING_LABEL = "A{0, number, integer}";

	public ArtificialLabel(int label) {
		super(getArtificialMarker(), label);
	}

	@Override
	public String toString() {
		return format(TO_STRING_LABEL, pharmacyID);
	}
}
