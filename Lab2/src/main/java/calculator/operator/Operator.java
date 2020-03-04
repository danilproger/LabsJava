package calculator.operator;

import context.Context;
import java.util.List;

public interface Operator {
    int getArgsAmount();
    void execute(Context context, List<String> args);
}
