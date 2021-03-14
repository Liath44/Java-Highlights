package md.jvac.datastructures.iodata;

import static java.text.MessageFormat.format;

public class TransactionSubject {
	private final static String TO_STRING_FORMAT = "{0} | {1, number, integer}";
	private final String name;
	private final int dailyVaccines;

	public TransactionSubject(String name, int dailyVaccines) {
		this.name = name;
		this.dailyVaccines = dailyVaccines;
	}

	@Override
	public String toString() {
		return format(TO_STRING_FORMAT, name, dailyVaccines);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof TransactionSubject) {
			return ((TransactionSubject) object).getName().equals(name);
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public int getDailyVaccines() {
		return dailyVaccines;
	}
}
