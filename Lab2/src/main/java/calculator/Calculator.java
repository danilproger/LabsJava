package calculator;

import calculator.operator.Operator;
import context.Context;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Calculator {
	private Context context;

	public Calculator() {
		context = new Context();
	}

	public Calculator(Context context) {
		this.context = context;
	}

	public void calculate(String fileName) throws IOException {
		Scanner scanner = fileName.equals("") ? new Scanner(System.in) : new Scanner(new File(fileName));

		while (scanner.hasNextLine()) {
			String line, operatorName;
			List<String> words, args = new LinkedList<>();

			line = scanner.nextLine();
			if (line.equals("")) continue;

			words = Arrays.asList(line.split("\\s+"));

			operatorName = words.get(0);
			for (int i = 1; i < words.size(); ++i) {
				args.add(words.get(i));
			}

			Operator operator = OperatorFactory.getInstance().createOperator(operatorName);

			if (operator == null) continue;

			operator.execute(context, args);
			Logger.getLogger("CalculatorLogger").info("operator " + operatorName + " was created and executed");
		}
	}
}