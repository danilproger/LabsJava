package calculator;

import calculator.operator.Operator;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class OperatorFactory {
    private volatile static OperatorFactory factory = null;
    private final Properties properties = new Properties();

    private OperatorFactory() {
        Logger.getLogger("CalculatorLogger").info("Operator factory was created!");

        try {
            InputStream in = OperatorFactory.class.getClassLoader().getResourceAsStream("operators.properties");
            properties.load(in);
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static OperatorFactory getInstance() {
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
        Operator operator = null;

        try {
            operator = (Operator) Class.forName(properties.getProperty(name)).getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return operator;
    }
}
