package viewcontroller.view;

import javax.swing.*;
import java.awt.*;

public class GameOverFrame extends JPanel {
	private final JLabel scoreLabel;

	public GameOverFrame() {
		setLayout(new GridLayout(6, 1));
		setFocusable(true);
		requestFocusInWindow();

		Font textFont = new Font("Monospaced", Font.PLAIN, 30);

		add(textField("GAME OVER", textFont), this);

		add(lineLabel(), this);

		scoreLabel = textField("", textFont);
		add(scoreLabel, this);
		updateScore(0);

		add(textField("Play again? Y/N", textFont), this);

		add(lineLabel(), this);

		add(textField("Press key to continue", textFont), this);
	}

	public void updateScore(int score) {
		scoreLabel.setText("Score:  " + score);
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
