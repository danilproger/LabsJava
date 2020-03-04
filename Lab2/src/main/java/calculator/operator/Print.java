package calculator.operator;

import context.Context;
import java.util.EmptyStackException;
import java.util.List;
import java.util.logging.Logger;

public class Print implements Operator {
    @Override
    public int getArgsAmount() {
        return 0;
    }

    @Override
    public void execute(Context context, List<String> args) {
        if (context.getStackSize() < 1) throw new EmptyStackException();

        if(context.getStackSize() < 1) throw new EmptyStackException();
        double value = context.peek();

        System.out.println(value);
        Logger.getLogger("CalculatorLogger").info("printed value = " + value);
    }
}