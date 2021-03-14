package md.jvac.simplex;

import md.jvac.datastructures.labels.SimplexLabel;
import md.jvac.datastructures.matrices.SimplexTableau;
import md.jvac.rationalnumber.RationalNumber;

public abstract class PivotingHelper {
	public abstract boolean shouldUpdateNegativeMinimum(SimplexTableau tableau, RationalNumber number, RationalNumber negativeMinimum, int x);

	public abstract boolean shouldUpdateRatio(SimplexLabel label, RationalNumber currentRatio, RationalNumber minimumRatio);
}
