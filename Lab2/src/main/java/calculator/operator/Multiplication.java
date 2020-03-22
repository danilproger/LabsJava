package calculator.operator;

import context.Context;

import java.util.EmptyStackException;
import java.util.List;
import java.util.logging.Logger;

public class Multiplication implements Operator {

	@Override
	public void execute(Context context, List<String> args) {
		if (context.getStackSize() < 2) throw new EmptyStackException();

		double left = context.pop();
		double right = context.pop();

		context.push(left * right);

		Logger.getLogger("CalculatorLogger").info("mult = " + (left * right));
	}
}