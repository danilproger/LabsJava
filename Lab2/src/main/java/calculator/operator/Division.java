package calculator.operator;

import context.Context;

import java.util.EmptyStackException;
import java.util.List;
import java.util.logging.Logger;

public class Division implements Operator {

	@Override
	public void execute(Context context, List<String> args) {
		if (context.getStackSize() < 2) throw new EmptyStackException();

		double right = context.pop();
		double left = context.pop();

		context.push(left / right);
		Logger.getLogger("CalculatorLogger").info("div = " + (left / right));
	}
}