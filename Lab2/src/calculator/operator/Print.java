package calculator.operator;

import context.Context;

import java.util.ArrayList;

public class Print implements Operator {
    @Override
    public int getArgsAmount() {
        return 0;
    }

    @Override
    public void execute(Context context, ArrayList<String> args) {
        System.out.println(context.getNumbers().peek());
    }
}