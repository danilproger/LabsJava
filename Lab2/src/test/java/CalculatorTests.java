import calculator.Calculator;
import context.Context;
import junit.framework.TestCase;

import java.io.IOException;

public class CalculatorTests extends TestCase {
	public void testPush() throws IOException {
		Context context = new Context();
		Calculator calculator = new Calculator(context);

		calculator.calculate("src/test/resources/push.txt");

		assertEquals(context.getStackSize(), 3);
	}

	public void testPop() throws IOException {
		Context context = new Context();
		Calculator calculator = new Calculator(context);

		calculator.calculate("src/test/resources/push.txt");
		assertEquals(context.getStackSize(), 3);

		calculator.calculate("src/test/resources/pop.txt");
		assertEquals(context.getStackSize(), 1);
	}

	public void testDefinition() throws IOException {
		Context context = new Context();
		Calculator calculator = new Calculator(context);

		calculator.calculate("src/test/resources/def.txt");

		assertEquals(context.getStackSize(), 3);

		assertTrue(context.contains("A"));
		assertTrue(context.contains("B"));
	}

	public void testPrint() throws IOException {
		Context context = new Context();
		Calculator calculator = new Calculator(context);

		calculator.calculate("src/test/resources/print.txt");

		assertEquals(context.getStackSize(), 1);
	}

	public void testAdd() throws IOException {
		Context context = new Context();
		Calculator calculator = new Calculator(context);

		calculator.calculate("src/test/resources/add.txt");

		assertEquals(context.peek(), 9.0);
	}

	public void testSub() throws IOException {
		Context context = new Context();
		Calculator calculator = new Calculator(context);

		calculator.calculate("src/test/resources/sub.txt");

		assertEquals(context.peek(), -5.0);
	}

	public void testMult() throws IOException {
		Context context = new Context();
		Calculator calculator = new Calculator(context);

		calculator.calculate("src/test/resources/mult.txt");

		assertEquals(context.peek(), 63.0);
	}

	public void testDiv() throws IOException {
		Context context = new Context();
		Calculator calculator = new Calculator(context);

		calculator.calculate("src/test/resources/div.txt");

		assertEquals(context.peek(), 15.0);
	}

	public void testSqrt() throws IOException {
		Context context = new Context();
		Calculator calculator = new Calculator(context);

		calculator.calculate("src/test/resources/sqrt.txt");

		assertEquals(context.peek(), 16.0);
	}

	public void testArithmetic() throws IOException {
		Context context = new Context();
		Calculator calculator = new Calculator(context);

		calculator.calculate("src/test/resources/arithmetic.txt");

		assertEquals(context.peek(), 4.0);
	}
}
