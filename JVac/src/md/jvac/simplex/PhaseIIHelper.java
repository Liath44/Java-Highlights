package md.jvac.simplex;

import md.jvac.datastructures.labels.SimplexLabel;
import md.jvac.datastructures.matrices.SimplexTableau;
import md.jvac.rationalnumber.RationalNumber;

public class PhaseIIHelper extends PivotingHelper {
	@Override
	public boolean shouldUpdateNegativeMinimum(SimplexTableau tableau, RationalNumber number, RationalNumber negativeMinimum, int x) {
		return false;
	}

	@Override
	public boolean shouldUpdateRatio(SimplexLabel label, RationalNumber currentRatio, RationalNumber minimumRatio) {
		return false;
	}
}
