package viewcontroller.view;

import config.GameConfig;
import model.entities.Dot;
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JPanel {
	private Dot[] field;
	private int score;

	public void setField(Dot[] field) {
		this.field = field;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public GameFrame(){
		setLayout(new BorderLayout());
		setFocusable(true);
		requestFocusInWindow();

		Font textFont = new Font("Monospaced", Font.PLAIN, 28);

		JTextField scoreField = new JTextField();
		scoreField.setFont(textFont);
		scoreField.setEditable(false);

		JComponent gameField = new JComponent() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				scoreField.setText("Score: " + score);
				Graphics2D g2 = (Graphics2D) g;
				Image empty = new ImageIcon(getClass().getResource("/images/empty.png")).getImage();
				Image head = new ImageIcon(getClass().getResource("/images/head.png")).getImage();
				Image body = new ImageIcon(getClass().getResource("/images/body.png")).getImage();
				Image apple = new ImageIcon(getClass().getResource("/images/fruit.png")).getImage();

				for (int i = 0; i < GameConfig.FIELD_HEIGHT; i++) {
					for (int j = 0; j < GameConfig.FIELD_WIDTH; j++) {
						switch(field[i*GameConfig.FIELD_HEIGHT + j]){
							case BODY:
								g2.drawImage(body, j*GameConfig.SCALE, i*GameConfig.SCALE, null);
								break;
							case EMPTY:
								g2.drawImage(empty, j*GameConfig.SCALE, i*GameConfig.SCALE, null);
								break;
							case HEAD:
								g2.drawImage(head, j*GameConfig.SCALE, i*GameConfig.SCALE, null);
								break;
							case APPLE:
								g2.drawImage(apple, j*GameConfig.SCALE, i*GameConfig.SCALE, null);
								break;
						}
					}
				}
			}
		};

		add(gameField);
		add(scoreField, BorderLayout.PAGE_END);
	}

}
