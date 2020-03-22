package calculator.operator;

import context.Context;

import java.util.List;
import java.util.logging.Logger;

public class Definition implements Operator {

	@Override
	public void execute(Context context, List<String> args) {
		if (args.size() != 2) throw new IllegalArgumentException();

		String key = args.get(0);
		double value = Double.parseDouble(args.get(1));

		context.put(key, value);
		Logger.getLogger("CalculatorLogger").info("definition '" + key + "' = " + value + " was defined");
	}
}