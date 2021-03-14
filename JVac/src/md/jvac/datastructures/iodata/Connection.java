package md.jvac.datastructures.iodata;

import md.jvac.rationalnumber.RationalNumber;

import static java.text.MessageFormat.format;

public class Connection {
	private final static String TO_STRING_FORMAT = "{0, number, integer} | {1, number, integer} | {2, number, integer} | {3}";
	private final int producentID;
	private final int pharmacyID;
	private final int dailyVaccines;
	private final RationalNumber vaccineCost;

	public Connection(int producentID, int pharmacyID, int dailyVaccines, RationalNumber vaccineCost) {
		this.producentID = producentID;
		this.pharmacyID = pharmacyID;
		this.dailyVaccines = dailyVaccines;
		this.vaccineCost = vaccineCost;
	}

	@Override
	public String toString() {
		return format(TO_STRING_FORMAT, producentID, pharmacyID, dailyVaccines, vaccineCost.toString());
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Connection) {
			Connection connection = (Connection) object;
			return connection.getPharmacyID() == pharmacyID && connection.getProducentID() == producentID;
		}
		return false;
	}

	public int getProducentID() {
		return producentID;
	}

	public int getPharmacyID() {
		return pharmacyID;
	}

	public int getDailyVaccines() {
		return dailyVaccines;
	}

	public RationalNumber getVaccineCost() {
		return vaccineCost;
	}
}
