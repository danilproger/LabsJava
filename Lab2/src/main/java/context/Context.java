package context;

import java.util.HashMap;
import java.util.Stack;

public class Context {
    private Stack<Double> numbers;
    private HashMap<String, Double> definitions;

    public Context() {
        numbers = new Stack<>();
        definitions = new HashMap<>();
    }

    public int getStackSize() {
        return numbers.size();
    }

    public void push(double value) {
        numbers.push(value);
    }

    public double pop() {
        return numbers.pop();
    }

    public double peek() {
        return numbers.peek();
    }

    public void put(String key, double value) {
        definitions.put(key, value);
    }

    public double get(String key) {
        return definitions.get(key);
    }

    public boolean contains(String key) {
        return definitions.containsKey(key);
    }
}
