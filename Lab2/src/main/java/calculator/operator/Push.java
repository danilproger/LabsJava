package calculator.operator;

import context.Context;
import java.util.List;
import java.util.logging.Logger;

public class Push implements Operator {
    @Override
    public int getArgsAmount() {
        return 1;
    }

    @Override
    public void execute(Context context, List<String> args) {
        if(args.size() < getArgsAmount()) throw new IllegalArgumentException();

        double value;

        try {
            value = Double.parseDouble(args.get(0));
        } catch (NumberFormatException e) {
            try {
                value = context.get(args.get(0));
            } catch (NullPointerException ex) {
                throw new IllegalArgumentException();
            }
        }

        context.push(value);
        Logger.getLogger("CalculatorLogger").info("pushed value = " + value);
    }
}