package viewcontroller.view;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class ScoreFrame extends JPanel {
	private final Properties properties;
	private final ArrayList<JLabel> scoreLabelList;
	private final ArrayList<String> scoreList;

	public ScoreFrame() {
		setLayout(new GridLayout(10, 1));
		setFocusable(true);
		requestFocusInWindow();

		properties = new Properties();

		Font textFont = new Font("Monospaced", Font.PLAIN, 30);
		Font scoreFont = new Font("Monospaced", Font.PLAIN, 40);
		scoreLabelList = new ArrayList<>();
		scoreList = new ArrayList<>();

		add(textField("Score", textFont));

		add(lineLabel(), this);

		for (int i = 0; i < 5; i++) {
			scoreLabelList.add(textField("", scoreFont));
			add(scoreLabelList.get(i), this);
		}

		add(lineLabel(), this);

		add(textField("Press q to quit", textFont), this);
	}

	public void updateScore() {
		loadProperties();
		scoreList.clear();

		for (var name : properties.stringPropertyNames()) {
			scoreList.add(properties.getProperty(name));
		}

		for (int i = 0; i < 5; i++) {
			if (i >= scoreList.size()) scoreLabelList.get(i).setText("");
			else scoreLabelList.get(i).setText((i + 1) + "  -  " + scoreList.get(i));
		}
		repaint();
	}

	private void loadProperties() {
		try {
			File prop = new File("src/main/resources/score.properties");
			InputStream io = new FileInputStream(prop);
			properties.load(io);
			io.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		line.setText("____________________");
		return line;
	}
}
