package calculator.operator;

import context.Context;
import java.util.List;

public class Comment implements Operator {
    @Override
    public int getArgsAmount() {
        return 1;
    }

    @Override
    public void execute(Context context, List<String> args) {

    }
}
