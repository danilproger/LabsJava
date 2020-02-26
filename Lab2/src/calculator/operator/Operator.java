package calculator.operator;

import context.Context;
import java.util.ArrayList;

public interface Operator {
    public int getArgsAmount();
    public void execute(Context context, ArrayList<String> args);
}
