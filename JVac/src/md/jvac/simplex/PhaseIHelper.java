package md.jvac.simplex;

import md.jvac.datastructures.labels.SimplexLabel;
import md.jvac.datastructures.matrices.SimplexTableau;
import md.jvac.rationalnumber.RationalNumber;

public class PhaseIHelper extends PivotingHelper {
	@Override
	public boolean shouldUpdateNegativeMinimum(SimplexTableau tableau, RationalNumber number, RationalNumber negativeMinimum, int x) {
		return number.isNegative() && number.equals(negativeMinimum) && tableau.willEliminateArtificial(x, this);
	}

	@Override
	public boolean shouldUpdateRatio(SimplexLabel label, RationalNumber currentRatio, RationalNumber minimumRatio) {
		return currentRatio.equals(minimumRatio) && label.isArtificial();
	}
}
