package model;

import config.GameConfig;
import model.entities.Direction;
import model.entities.Dot;
import model.entities.GameStatus;
import observer.Observable;
import observer.Observer;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Model implements Observable {
	private ArrayList<Point> snakeBody;
	private Deque<Direction> direction;

	private Point apple;
	private Dot[] field;

	private Timer timer;
	private TimerTask timerTask;

	private Direction currentDirection;
	private GameStatus gameStatus;
	private int score;
	private int difficulty;

	private final ArrayList<Observer> observers;

	public Model() {
		observers = new ArrayList<>();

		initGame();
		notifyObservers();
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public void setDirection(Direction dir) {
		direction.addLast(dir);
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public int getScore() {
		return score;
	}

	public Dot[] getField() {
		return field;
	}

	private void gameStep() {
		move();
		checkCollisions();

		if (gameStatus == GameStatus.GAME_OVER) {
			timer.cancel();
			saveData();
		}

		if (getHead().x == apple.x && getHead().y == apple.y) {
			snakeBody.add(0, new Point(apple));

			score += 50;
			generateApple();
		}

		updateField();
		notifyObservers();
	}

	private void saveData() {
		try {
			File prop = new File("src/main/resources/score.properties");
			InputStream in;
			OutputStream out;

			Properties properties = new Properties();
			in = new FileInputStream(prop);

			properties.load(in);

			ArrayList<Integer> scoreList = new ArrayList<>();
			for (var name : properties.stringPropertyNames()) {
				scoreList.add(Integer.valueOf(properties.getProperty(name)));
			}
			in.close();

			properties.clear();
			scoreList.add(score);
			Collections.sort(scoreList);

			int length = Math.min(scoreList.size(), 5);
			for (int i = 0; i < length; i++) {
				properties.put(String.valueOf(i + 1), String.valueOf(scoreList.get(scoreList.size() - 1 - i)));
			}

			out = new FileOutputStream(prop);
			properties.store(out, null);

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initGame() {
		field = new Dot[GameConfig.FIELD_HEIGHT * GameConfig.FIELD_WIDTH];
		apple = new Point();
		timer = new Timer();

		direction = new ArrayDeque<>();
		snakeBody = new ArrayList<>(GameConfig.FIELD_HEIGHT * GameConfig.FIELD_WIDTH);

		for (int i = 0; i < GameConfig.SNAKE_BASE_LENGTH; i++) {
			snakeBody.add(new Point(i, 0));
		}

		for (int i = 0; i < GameConfig.FIELD_WIDTH * GameConfig.FIELD_HEIGHT; i++) {
			field[i] = Dot.EMPTY;
		}

		score = 0;

		generateApple();
		updateField();

		difficulty = 1;
		gameStatus = GameStatus.IN_GAME;
		direction.addLast(Direction.RIGHT);
	}

	private void updateField() {
		for (int i = 0; i < GameConfig.FIELD_HEIGHT; i++) {
			for (int j = 0; j < GameConfig.FIELD_WIDTH; j++) {
				field[i * GameConfig.FIELD_HEIGHT + j] = Dot.EMPTY;
			}
		}

		for (var p : snakeBody) {
			field[p.y * GameConfig.FIELD_HEIGHT + p.x] = Dot.BODY;
		}

		field[getHead().y * GameConfig.FIELD_HEIGHT + getHead().x] = Dot.HEAD;
		field[apple.y * GameConfig.FIELD_HEIGHT + apple.x] = Dot.APPLE;
	}

	private void move() {
		Direction dir = direction.pollFirst();

		if (dir != null && currentDirection != dir.reverseDirection()) currentDirection = dir;

		for (int i = 0; i < snakeBody.size() - 1; i++) {
			snakeBody.get(i).setLocation(snakeBody.get(i + 1));
		}

		switch (currentDirection) {
			case RIGHT:
				getHead().x++;
				if (getHead().x >= GameConfig.FIELD_WIDTH) getHead().x = 0;
				break;
			case LEFT:
				getHead().x--;
				if (getHead().x < 0) getHead().x = GameConfig.FIELD_WIDTH - 1;
				break;
			case DOWN:
				getHead().y++;
				if (getHead().y >= GameConfig.FIELD_HEIGHT) getHead().y = 0;
				break;
			case UP:
				getHead().y--;
				if (getHead().y < 0) getHead().y = GameConfig.FIELD_HEIGHT - 1;
				break;
		}
	}

	private void generateApple() {
		Random random = new Random();
		int x, y;
		do {
			x = random.nextInt(GameConfig.FIELD_WIDTH);
			y = random.nextInt(GameConfig.FIELD_HEIGHT);
		} while (field[y * GameConfig.FIELD_WIDTH + x] != Dot.EMPTY);

		apple.x = x;
		apple.y = y;
	}

	private void checkCollisions() {
		for (int i = 0; i < snakeBody.size() - 1; i++) {
			if (snakeBody.get(i).x == getHead().x && snakeBody.get(i).y == getHead().y) {
				gameStatus = GameStatus.GAME_OVER;
				break;
			}
		}
	}

	private Point getHead() {
		return snakeBody.get(snakeBody.size() - 1);
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer obs : observers) obs.handleEvent();
	}

	public void start() {
		int period = 0;
		switch (difficulty) {
			case 1:
				period = 250;
				break;
			case 2:
				period = 200;
				break;
			case 3:
				period = 100;

		}

		initGame();
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				gameStep();
			}
		};
		timer.schedule(timerTask, 0, period);
	}
}
