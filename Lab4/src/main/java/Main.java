
import production.Factory;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
	private final static String TAG_STOP = "/s";
	private final static Scanner in = new Scanner(System.in);

	private final static Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws Exception {

		FileHandler fileHandler = new FileHandler("src/main/resources/calculator_logs.log");
		logger.addHandler(fileHandler);
		fileHandler.setFormatter(new SimpleFormatter());
		logger.info("Initial log");

		Factory production = new Factory();
		production.startProduction();
		if (in.nextLine().equals(TAG_STOP)) {
			production.stopProduction();
		}
	}


}
