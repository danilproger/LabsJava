package viewcontroller.view;

import config.GameConfig;
import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
	private final StartFrame startFrame;
	private final GameOverFrame gameOverFrame;
	private final GameFrame gameFrame;
	private final ScoreFrame scoreFrame;
	private JPanel content;

	public View() {
		super(GameConfig.GAME_NAME);
		setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setFocusable(true);
		requestFocusInWindow();
		setResizable(false);

		startFrame = new StartFrame();
		gameOverFrame = new GameOverFrame();
		gameFrame = new GameFrame();
		scoreFrame = new ScoreFrame();
		setStartFrame();
	}

	public void setStartFrame() {
		getContentPane().removeAll();
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - GameConfig.FRAME_WIDTH / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - GameConfig.FRAME_HEIGHT / 2,
				GameConfig.FRAME_WIDTH,
				GameConfig.FRAME_HEIGHT
		);
		content = startFrame;
		add(startFrame, this);
		repaint();
		validate();
	}

	public JPanel getContent() {
		return content;
	}

	public void setGameFrame() {
		getContentPane().removeAll();
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - GameConfig.FIELD_WIDTH * GameConfig.SCALE / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - GameConfig.FIELD_HEIGHT * GameConfig.SCALE / 2,
				GameConfig.FIELD_WIDTH * GameConfig.SCALE + 8,
				GameConfig.FIELD_HEIGHT * GameConfig.SCALE + 75
		);
		content = gameFrame;
		add(gameFrame, this);
		repaint();
		validate();
	}

	public void setScoreFrame() {
		getContentPane().removeAll();
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - GameConfig.FRAME_WIDTH / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - GameConfig.FRAME_HEIGHT / 2,
				GameConfig.FRAME_WIDTH,
				GameConfig.FRAME_HEIGHT
		);
		scoreFrame.updateScore();
		content = scoreFrame;
		add(scoreFrame, this);
		repaint();
		validate();
	}

	public void setGameOverFrame(int score){
		getContentPane().removeAll();
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - GameConfig.FRAME_WIDTH / 2,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - GameConfig.FRAME_HEIGHT / 2,
				GameConfig.FRAME_WIDTH,
				GameConfig.FRAME_HEIGHT
		);
		gameOverFrame.updateScore(score);
		content = gameOverFrame;
		add(gameOverFrame, this);
		repaint();
		validate();
	}

	public StartFrame getStartFrame() {
		return startFrame;
	}

	public GameOverFrame getGameOverFrame() {
		return gameOverFrame;
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public ScoreFrame getScoreFrame() {
		return scoreFrame;
	}

}
