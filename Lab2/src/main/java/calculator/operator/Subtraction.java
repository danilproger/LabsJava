package calculator.operator;

import context.Context;
import java.util.EmptyStackException;
import java.util.List;
import java.util.logging.Logger;

public class Subtraction implements Operator {
    @Override
    public int getArgsAmount() {
        return 0;
    }

    @Override
    public void execute(Context context, List<String> args) {
        if (context.getStackSize() < 2) throw new EmptyStackException();

        double left = context.pop();
        double right = context.pop();

        context.push(right - left);
        Logger.getLogger("CalculatorLogger").info("sub = " + (right - left));
    }
}