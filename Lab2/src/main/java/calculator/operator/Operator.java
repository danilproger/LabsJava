package calculator.operator;

import context.Context;
import java.util.List;

public interface Operator {
    void execute(Context context, List<String> args);
}
