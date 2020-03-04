package calculator.operator;

import context.Context;
import java.util.EmptyStackException;
import java.util.List;
import java.util.logging.Logger;

public class Sqrt implements Operator {
    @Override
    public int getArgsAmount() {
        return 0;
    }

    @Override
    public void execute(Context context, List<String> args) {
        if (context.getStackSize() < 1) throw new EmptyStackException();

        double result, arg;

        arg = context.pop();
        if(arg < 0) {
            result = arg;
            Logger.getLogger("CalculatorLogger").warning("arg: " + arg + " < 0, arg was pushed");
        } else {
            result = Math.sqrt(arg);
        }

        context.push(result);
        Logger.getLogger("CalculatorLogger").info("sqrt = " + result);
    }
}
