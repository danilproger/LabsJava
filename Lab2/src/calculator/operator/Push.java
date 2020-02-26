package calculator.operator;

import context.Context;

import java.util.ArrayList;

public class Push implements Operator {
    @Override
    public int getArgsAmount() {
        return 1;
    }

    @Override
    public void execute(Context context, ArrayList<String> args) {
        try {
            context.getNumbers().push(Double.parseDouble(args.get(0)));
        } catch (NumberFormatException e) {
            context.getDefinitions().put(args.get(0), Double.parseDouble(args.get(1)));
            context.getNumbers().push(Double.parseDouble(args.get(1)));
        }
    }
}