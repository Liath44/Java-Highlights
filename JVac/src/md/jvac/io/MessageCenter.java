package md.jvac.io;

public class MessageCenter {
	private MessageCenter() {
	}

	public static void logException(Exception exception) {
		exception.printStackTrace();
	}

	public static void logMessage(String message) {
		System.out.println(message);
	}
}
