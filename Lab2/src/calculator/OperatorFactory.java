package calculator;

import calculator.operator.Operator;
import java.io.IOException;
import java.util.Properties;

public class OperatorFactory {
    private static OperatorFactory factory;
    private final Properties properties = new Properties();

    private OperatorFactory() {
        try {
            properties.load(Calculator.class.getResourceAsStream("operators.properties"));
        } catch (IOException e) {
            System.out.println("aa");
        }
    }

    public static OperatorFactory getInstance() {
        if (factory == null) {
            factory = new OperatorFactory();
        }
        return factory;
    }

    public Operator createOperator(String name) {
        Operator operator = null;
        try {
            operator = (Operator) Class.forName(properties.getProperty(name)).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return operator;
    }
}
