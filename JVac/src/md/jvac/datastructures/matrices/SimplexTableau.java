package md.jvac.datastructures.matrices;

import md.jvac.datastructures.iodata.OutcomeNode;
import md.jvac.datastructures.labels.SimplexLabel;
import md.jvac.exceptions.ContradictoryDataException;
import md.jvac.rationalnumber.RationalNumber;
import md.jvac.simplex.PivotingHelper;

import java.util.ArrayList;
import java.util.List;

public class SimplexTableau extends Matrix<RationalNumber> {
	private final static String FUNCTION_COEFFICIENTS_LABEL = "function coefficients (Cj): ";
	private final static String RELATIVE_PROFIT_LABEL = "relative profit (Cj-Zj): ";
	private final static String BASIC_COEFFICIENTS_LABEL = "basic coefficients (Cb): ";
	private final static String BASIC_LABELS_LABEL = "basic labels (labels left): ";
	private final static String FUNCTION_LABELS_LABEL = "function labels (labels top): ";
	private List<RationalNumber> functionCoefficients;
	private final List<RationalNumber> relativeProfit;
	private List<RationalNumber> basicCoefficients;
	private List<SimplexLabel> basicLabels;
	private List<SimplexLabel> functionLabels;

	public SimplexTableau(int xSize, int ySize, RationalNumber defaultValue) {
		super(xSize, ySize, defaultValue);
		relativeProfit = new ArrayList<>();
		for (int i = 0; i < getXSize(); i++) {
			relativeProfit.add(new RationalNumber(1));
		}
	}

	public void eliminateWithGauss(int x, int y) {
		prepareEliminatingRow(x, y);
		for (int i = y - 1; i >= 0; i--) {
			eliminateRowWithGauss(getRow(i), y, x);
		}
		for (int j = y + 1; j < getYSize(); j++) {
			eliminateRowWithGauss(getRow(j), y, x);
		}
		eliminateRowWithGauss(relativeProfit, y, x);
	}

	private void prepareEliminatingRow(int x, int y) {
		RationalNumber multiplier = getElement(x, y).getReversed();
		for (int xIndex = 0; xIndex < getXSize(); xIndex++) {
			setElement(xIndex, y, getElement(xIndex, y).multiply(multiplier));
		}
	}

	private void eliminateRowWithGauss(List<RationalNumber> eliminatedRow, int subtractingRow, int subtractingColumn) {
		RationalNumber multiplier = eliminatedRow.get(subtractingColumn);
		for (int i = 0; i < eliminatedRow.size(); i++) {
			eliminatedRow.set(i, eliminatedRow.get(i).subtract(getElement(i, subtractingRow).multiply(multiplier)));
		}
	}

	public void calculateRelativeProfit() {
		for (int x = 0; x < functionCoefficients.size(); x++) {
			RationalNumber relativeSubtrahend = new RationalNumber(0);
			for (int y = 0; y < getYSize(); y++) {
				relativeSubtrahend = relativeSubtrahend.add(getElement(x, y).multiply(basicCoefficients.get(y)));
			}
			relativeProfit.set(x, functionCoefficients.get(x).subtract(relativeSubtrahend));
		}
	}

	public SimplexLabel updateBasicLists(int enteringColumnIndex, int leavingRowIndex) {
		SimplexLabel oldLabel = basicLabels.get(leavingRowIndex);
		basicLabels.set(leavingRowIndex, functionLabels.get(enteringColumnIndex));
		basicCoefficients.set(leavingRowIndex, functionCoefficients.get(enteringColumnIndex));
		return oldLabel;
	}

	public void updateBasicCoefficients() {
		for (int i = 0; i < basicCoefficients.size(); i++) {
			SimplexLabel correspondingLabel = basicLabels.get(i);
			int functionCoefficientIndex = getFunctionLabelIndex(correspondingLabel);
			basicCoefficients.set(i, functionCoefficients.get(functionCoefficientIndex));
		}
	}

	public void verifyZeroedArtificialVariables() throws ContradictoryDataException {
		for (int i = 0; i < basicLabels.size(); i++) {
			if (basicLabels.get(i).isArtificial() && !getOutcome(i).isZero()) {
				throw new ContradictoryDataException();
			}
		}
	}

	public void removeColumn(SimplexLabel removedLabel) {
		int removedColumnIndex = getFunctionLabelIndex(removedLabel);
		super.removeColumn(removedColumnIndex);
		functionCoefficients.remove(removedColumnIndex);
		functionLabels.remove(removedColumnIndex);
		relativeProfit.remove(removedColumnIndex);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		buildList(builder, FUNCTION_COEFFICIENTS_LABEL, functionCoefficients);
		buildList(builder, RELATIVE_PROFIT_LABEL, relativeProfit);
		buildList(builder, BASIC_COEFFICIENTS_LABEL, basicCoefficients);
		buildList(builder, BASIC_LABELS_LABEL, basicLabels);
		buildList(builder, FUNCTION_LABELS_LABEL, functionLabels);
		builder.append(super.toString());
		return builder.toString();
	}

	private void buildList(StringBuilder builder, String label, List<?> list) {
		builder.append(label);
		builder.append(list.toString());
		builder.append("\n");
	}

	public void setArtificialFunctionCoefficients() {
		for (int i = 0; i < functionLabels.size(); i++) {
			if (functionLabels.get(i).isArtificial()) {
				functionCoefficients.set(i, new RationalNumber(1));
			} else {
				functionCoefficients.set(i, new RationalNumber(0));
			}
		}
	}

	public void setFunctionCoefficients(List<RationalNumber> functionCoefficients) {
		this.functionCoefficients = functionCoefficients;
	}

	public void setBasicCoefficients(List<RationalNumber> basicCoefficients) {
		this.basicCoefficients = basicCoefficients;
	}

	public void setBasicLabels(List<SimplexLabel> basicLabels) {
		this.basicLabels = basicLabels;
	}

	public void setFunctionLabels(List<SimplexLabel> functionLabels) {
		this.functionLabels = functionLabels;
	}

	public int getEnteringColumnIndex(PivotingHelper helper) {
		RationalNumber negativeMinimum = new RationalNumber(0);
		int negativeMinimumIndex = -1;
		for (int i = 0; i < relativeProfit.size(); i++) {
			RationalNumber number = relativeProfit.get(i);
			if (shouldUpdateNegativeMinimum(i, number, negativeMinimum, helper)) {
				negativeMinimum = number;
				negativeMinimumIndex = i;
			}
		}
		return negativeMinimumIndex;
	}

	private boolean shouldUpdateNegativeMinimum(int x, RationalNumber number, RationalNumber negativeMinimum, PivotingHelper helper) {
		return number.isSmaller(negativeMinimum) || helper.shouldUpdateNegativeMinimum(this, number, negativeMinimum, x);
	}

	public boolean willEliminateArtificial(int enteringColumnIndex, PivotingHelper helper) {
		int leavingRowIndex = getLeavingRowIndex(enteringColumnIndex, helper);
		return leavingRowIndex != -1 && basicLabels.get(leavingRowIndex).isArtificial();
	}

	public int getLeavingRowIndex(int enteringColumnIndex, PivotingHelper helper) {
		RationalNumber minimumRatio = null;
		int minimumRatioIndex = -1;
		for (int y = 0; y < basicCoefficients.size(); y++) {
			RationalNumber divisor = getElement(enteringColumnIndex, y);
			if (divisor.isPositive() && !getOutcome(y).isNegative()) {
				RationalNumber currentRatio = getOutcome(y).divide(divisor);
				if (shouldUpdateRatio(y, currentRatio, minimumRatio, helper)) {
					minimumRatioIndex = y;
					minimumRatio = currentRatio;
				}
			}
		}
		return minimumRatioIndex;
	}

	private boolean shouldUpdateRatio(int y, RationalNumber currentRatio, RationalNumber minimumRatio, PivotingHelper helper) {
		return minimumRatio == null || currentRatio.isSmaller(minimumRatio) || helper.shouldUpdateRatio(basicLabels.get(y), currentRatio, minimumRatio);
	}

	public List<OutcomeNode> getOutcome() {
		List<OutcomeNode> outcome = new ArrayList<>();
		for (int y = 0; y < getYSize(); y++) {
			if (basicLabels.get(y).isVariable()) {
				outcome.add(new OutcomeNode(basicLabels.get(y), getOutcome(y)));
			}
		}
		return outcome;
	}

	private int getFunctionLabelIndex(SimplexLabel label) {
		for (int i = functionLabels.size() - 1; i >= 0; i--) {
			if (functionLabels.get(i).equals(label)) {
				return i;
			}
		}
		return -1;
	}

	public List<RationalNumber> getFunctionCoefficientsClone() {
		List<RationalNumber> clone = new ArrayList<>();
		for (int i = 0; i < functionLabels.size(); i++) {
			if (!functionLabels.get(i).isArtificial()) {
				clone.add(functionCoefficients.get(i));
			}
		}
		return clone;
	}

	public RationalNumber getOutcome(int y) {
		return getElement(getXSize() - 1, y);
	}
}
