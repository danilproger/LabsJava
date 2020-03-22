import calculator.Calculator;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
	static Logger logger = Logger.getLogger("CalculatorLogger");

	public static void main(String[] args) throws IOException {
		setUpLogger();

		Calculator calculator = new Calculator();
		calculator.calculate("src/main/resources/in.txt");
	}

	private static void setUpLogger() {
		try {
			FileHandler fileHandler = new FileHandler("src/main/resources/calculator_logs.log");
			logger.addHandler(fileHandler);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.info("Initial log");
		} catch (IOException | SecurityException ex) {
			ex.printStackTrace();
		}
	}
}