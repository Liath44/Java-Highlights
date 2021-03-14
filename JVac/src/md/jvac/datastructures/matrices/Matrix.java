package md.jvac.datastructures.matrices;

import md.jvac.exceptions.InvalidColumnSizeException;

import java.util.ArrayList;
import java.util.List;

public class Matrix<T> {
	protected List<List<T>> matrix;

	public Matrix(int xSize, int ySize, T defaultValue) {
		matrix = new ArrayList<>();
		for (int i = 0; i < ySize; i++) {
			ArrayList<T> row = new ArrayList<>();
			for (int j = 0; j < xSize; j++) {
				row.add(defaultValue);
			}
			matrix.add(row);
		}
	}

	public void addColumn(List<T> column) {
		if (column.size() != getYSize()) {
			throw new InvalidColumnSizeException(column.size(), getYSize());
		}
		for (int i = 0; i < column.size(); i++) {
			getRow(i).add(column.get(i));
		}
	}

	public void removeColumn(int position) {
		for (List<T> row : matrix) {
			row.remove(position);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (List<T> row : matrix) {
			for (T element : row) {
				builder.append(element);
				builder.append(" ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	public T getElement(int x, int y) {
		return getRow(y).get(x);
	}

	public void setElement(int x, int y, T element) {
		getRow(y).set(x, element);
	}

	public List<T> getRow(int index) {
		return matrix.get(index);
	}

	public int getXSize() {
		if (matrix.size() == 0) {
			return 0;
		}
		return getRow(0).size();
	}

	public int getYSize() {
		return matrix.size();
	}
}
