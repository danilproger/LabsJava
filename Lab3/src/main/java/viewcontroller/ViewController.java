package viewcontroller;

import config.GameConfig;
import model.Model;
import model.entities.Direction;
import model.entities.GameStatus;
import observer.Observer;
import viewcontroller.view.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ViewController implements Observer {
	private final Model model;
	private final View view;

	ViewController() {
		view = new View();
		model = new Model();
		model.addObserver(this);

		initViews();
	}

	private void initViews() {
		view.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (view.getContent() == view.getStartFrame())
					switch (e.getKeyCode()) {
						case KeyEvent.VK_1:
							view.setGameFrame();
							model.setDifficulty(1);
							model.start();
							break;
						case KeyEvent.VK_2:
							view.setGameFrame();
							model.setDifficulty(2);
							model.start();
							break;
						case KeyEvent.VK_3:
							view.setGameFrame();
							model.setDifficulty(3);
							model.start();
							break;
						case KeyEvent.VK_S:
							view.setScoreFrame();
							break;
						case KeyEvent.VK_Q:
							view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
							break;
					}
				if (view.getContent() == view.getScoreFrame())
					if (e.getKeyCode() == KeyEvent.VK_Q) {
						view.setStartFrame();
					}
				if (view.getContent() == view.getGameOverFrame())
					switch (e.getKeyCode()) {
						case KeyEvent.VK_Y:
							view.setStartFrame();
							break;
						case KeyEvent.VK_N:
							view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
							break;
					}
				if (view.getContent() == view.getGameFrame())
					switch (e.getKeyCode()) {
						case KeyEvent.VK_UP:
						case KeyEvent.VK_W:
							model.setDirection(Direction.UP);
							break;
						case KeyEvent.VK_DOWN:
						case KeyEvent.VK_S:
							model.setDirection(Direction.DOWN);
							break;
						case KeyEvent.VK_LEFT:
						case KeyEvent.VK_A:
							model.setDirection(Direction.LEFT);
							break;
						case KeyEvent.VK_RIGHT:
						case KeyEvent.VK_D:
							model.setDirection(Direction.RIGHT);
							break;
					}
				view.getGameFrame().setField(model.getField());
			}
		});

	}

	@Override
	public void handleEvent() {
		if (model.getGameStatus() == GameStatus.IN_GAME) {
			view.getGameFrame().setScore(model.getScore());
			view.getGameFrame().repaint();
		}

		if (model.getGameStatus() == GameStatus.GAME_OVER) {
			view.setGameOverFrame(model.getScore());
		}
	}

	public static void main(String[] args) {
		new ViewController();
	}
}
