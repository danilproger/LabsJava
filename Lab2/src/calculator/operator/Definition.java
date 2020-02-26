package calculator.operator;

import context.Context;

import java.util.ArrayList;

public class Definition implements Operator {
    @Override
    public int getArgsAmount() {
        return 2;
    }

    @Override
    public void execute(Context context, ArrayList<String> args) {
        context.getDefinitions().put(args.get(0), Double.valueOf(args.get(1)));
    }
}
