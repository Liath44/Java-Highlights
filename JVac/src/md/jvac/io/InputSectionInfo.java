package md.jvac.io;

public class InputSectionInfo {
	private final int sectionIndex;
	private final String sectionName;
	private final String sectionHeader;
	private final int numberOfDataBits;

	public InputSectionInfo(int sectionIndex, String sectionName, String sectionHeader, int numberOfDataBits) {
		this.sectionIndex = sectionIndex;
		this.sectionName = sectionName;
		this.sectionHeader = sectionHeader;
		this.numberOfDataBits = numberOfDataBits;
	}

	public int getSectionIndex() {
		return sectionIndex;
	}

	public String getSectionName() {
		return sectionName;
	}

	public String getSectionHeader() {
		return sectionHeader;
	}

	public int getNumberOfDataBits() {
		return numberOfDataBits;
	}
}
