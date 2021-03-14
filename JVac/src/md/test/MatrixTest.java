package md.test;

import md.jvac.datastructures.matrices.Matrix;
import md.jvac.exceptions.InvalidColumnSizeException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MatrixTest {
	@Test
	public void ifConstructorIsCalledThenProperMatrixIsCreated() {
		//given
		Matrix<Integer> matrix = new Matrix<>(3, 2, 0);
		//then
		assertEquals(matrix.toString(), "0 0 0 \n0 0 0 \n");
	}

	@Test
	public void ifColumnIsAddedThenItIsAppendedToTheEnd() {
		//given
		Matrix<Integer> matrix = new Matrix<>(3, 2, 0);
		List<Integer> column = new ArrayList<>();
		column.add(1);
		column.add(1);
		//when
		matrix.addColumn(column);
		//then
		assertEquals(matrix.toString(), "0 0 0 1 \n0 0 0 1 \n");
	}

	@Test(expected = InvalidColumnSizeException.class)
	public void ifColumnOfInvalidSizeIsAddedThenExceptionIsThrown() {
		//given
		Matrix<Integer> matrix = new Matrix<>(3, 2, 0);
		List<Integer> column = new ArrayList<>();
		column.add(1);
		//when
		matrix.addColumn(column);
		//then
		fail();
	}

	@Test
	public void ifColumnIsRemovedThenMatrixChangesProperly() {
		//given
		Matrix<Integer> matrix = new Matrix<>(3, 2, 0);
		matrix.setElement(1, 0, 1);
		matrix.setElement(1, 1, 1);
		//when
		matrix.removeColumn(1);
		//then
		assertEquals(matrix.toString(), "0 0 \n0 0 \n");
	}

	@Test
	public void ifMatrixWasCreatedAsZeroByZeroThenItsSizeIsIndeed0By0() {
		//given
		Matrix<Integer> matrix = new Matrix<>(0, 0, 0);
		//then
		assertEquals(matrix.getXSize(), 0);
		assertEquals(matrix.getYSize(), 0);
	}

	@Test
	public void ifElementIsChengedThenMatrixesContentChanges() {
		//given
		Matrix<Integer> matrix = new Matrix<>(3, 2, 0);
		//when
		matrix.setElement(2, 1, 1);
		//then
		assertEquals(matrix.toString(), "0 0 0 \n0 0 1 \n");
	}

	@Test
	public void isGetterIsCalledThenProperElementIsProvided() {
		//given
		Matrix<Integer> matrix = new Matrix<>(3, 2, 0);
		matrix.setElement(2, 1, 1);
		//when
		int providedElement = matrix.getElement(2, 1);
		//then
		assertEquals(providedElement, 1);
	}
}
