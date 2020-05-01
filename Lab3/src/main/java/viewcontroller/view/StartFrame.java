package viewcontroller.view;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JPanel {
	public StartFrame() {
		setLayout(new GridLayout(8, 1));
		setFocusable(true);
		requestFocusInWindow();

		Font difficultyFont = new Font("Monospaced", Font.PLAIN, 45);
		Font textFont = new Font("Monospaced", Font.PLAIN, 30);

		add(textField("Choose difficulty", textFont), this);

		add(lineLabel(), this);

		add(textField("1 - Slow", difficultyFont), this);

		add(textField("2 - Fast", difficultyFont), this);

		add(textField("3 - Hard", difficultyFont), this);

		add(textField("S - score", difficultyFont), this);

		add(textField("q - Quit", difficultyFont), this);

		add(textField("Press key to continue", textFont), this);
	}

	private JLabel textField(String text, Font font) {
		JLabel textField = new JLabel();
		textField.setFont(font);
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText(text);
		return textField;
	}

	private JLabel lineLabel() {
		JLabel line = new JLabel();
		line.setFont(new Font("Monospaced", Font.PLAIN, 30));
		line.setText("_____________________");
		return line;
	}
}
