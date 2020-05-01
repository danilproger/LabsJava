package model.entities;

public enum Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT;
	public Direction reverseDirection() {
		switch (this) {
			case UP: return DOWN;
			case DOWN: return UP;
			case RIGHT: return LEFT;
			case LEFT: return RIGHT;
		}
		return null;
	}
}
