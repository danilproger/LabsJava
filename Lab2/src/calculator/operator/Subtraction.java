package calculator.operator;

import context.Context;
import java.util.ArrayList;

public class Subtraction implements Operator {
    @Override
    public int getArgsAmount() {
        return 0;
    }

    @Override
    public void execute(Context context, ArrayList<String> args) {
        double left = context.getNumbers().pop();
        double right = context.getNumbers().pop();
        context.getNumbers().push(right-left);
    }
}