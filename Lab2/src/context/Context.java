package context;

import java.util.HashMap;
import java.util.Stack;

public class Context {
    private Stack<Double> numbers;
    private HashMap<String, Double> definitions;

    Context(){
        numbers = new Stack<>();
        definitions = new HashMap<>();
    }

    public Stack<Double> getNumbers() {
        return numbers;
    }

    public HashMap<String, Double> getDefinitions() {
        return definitions;
    }
}
