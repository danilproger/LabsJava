package calculator.operator;

import context.Context;
import java.util.EmptyStackException;
import java.util.List;
import java.util.logging.Logger;

public class Pop implements Operator {
    @Override
    public int getArgsAmount() {
        return 0;
    }

    @Override
    public void execute(Context context, List<String> args) {
        if(context.getStackSize() < 1) throw new EmptyStackException();
        double value = context.pop();

        Logger.getLogger("CalculatorLogger").info("popped value = " + value);
    }
}
