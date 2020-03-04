package calculator;

import calculator.operator.Operator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class OperatorFactory {
    private volatile static OperatorFactory factory = null;
    private final Properties properties = new Properties();

    private OperatorFactory() throws IOException {
        Logger.getLogger("CalculatorLogger").info("Operator factory was created!");

        InputStream in = OperatorFactory.class.getClassLoader().getResourceAsStream("operators.properties");
        if (in != null) {
            properties.load(in);
            in.close();
        }
    }

    public static OperatorFactory getInstance() throws IOException {
        if (factory == null) {
            synchronized (OperatorFactory.class) {
                if (factory == null) {
                    factory = new OperatorFactory();
                }
            }
        }
        return factory;
    }

    public Operator createOperator(String name) {
        Operator operator;

        try {
            operator = (Operator) Class.forName(properties.getProperty(name)).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            Logger.getLogger("CalculatorLogger").warning("operator " + name + " was not found");
            return null;
        }

        return operator;
    }
}
