package md.jvac.simplex;

import md.jvac.datastructures.labels.SimplexLabel;
import md.jvac.datastructures.matrices.SimplexTableau;
import md.jvac.exceptions.ContradictoryDataException;
import md.jvac.rationalnumber.RationalNumber;

import java.util.List;

import static md.jvac.io.MessageCenter.logMessage;

public class SimplexSolver {
	private final static String BEGIN_PHASE_I_MESSAGE = "Finding initial solution";
	private final static String BEGIN_PHASE_II_MESSAGE = "Optimising solution";

	public SimplexSolver() {
	}

	public void performSimplex(SimplexTableau tableau) throws ContradictoryDataException {
		logMessage(BEGIN_PHASE_I_MESSAGE);
		List<RationalNumber> functionCoefficientsCopy = tableau.getFunctionCoefficientsClone();
		tableau.setArtificialFunctionCoefficients();
		tableau.calculateRelativeProfit();
		PivotingHelper helper = new PhaseIHelper();
		executePivotingSession(tableau, helper);
		tableau.verifyZeroedArtificialVariables();
		logMessage(BEGIN_PHASE_II_MESSAGE);
		prepareForPhaseII(tableau, functionCoefficientsCopy);
		helper = new PhaseIIHelper();
		executePivotingSession(tableau, helper);
	}

	private void executePivotingSession(SimplexTableau tableau, PivotingHelper helper) {
		int enteringColumnIndex = tableau.getEnteringColumnIndex(helper);
		while (enteringColumnIndex >= 0) {
			int leavingRowIndex = tableau.getLeavingRowIndex(enteringColumnIndex, helper);
			tableau.eliminateWithGauss(enteringColumnIndex, leavingRowIndex);
			SimplexLabel oldLabel = tableau.updateBasicLists(enteringColumnIndex, leavingRowIndex);
			if (oldLabel.isArtificial()) {
				tableau.removeColumn(oldLabel);
			}
			enteringColumnIndex = tableau.getEnteringColumnIndex(helper);
		}
	}

	private void prepareForPhaseII(SimplexTableau tableau, List<RationalNumber> functionCoefficients) {
		tableau.setFunctionCoefficients(functionCoefficients);
		tableau.updateBasicCoefficients();
		tableau.calculateRelativeProfit();
	}
}
