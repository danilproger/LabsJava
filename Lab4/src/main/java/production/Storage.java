package production;

import observer.Observable;
import observer.Observer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class Storage<T> implements Observable {
	private final ArrayList<Observer> observers;
	private final Queue<T> parts;
	private final int storageSize;
	private final Class<T> partType;

	public Storage(int storageSize, Class<T> partType) {
		observers = new ArrayList<>();
		this.parts = new ArrayDeque<>();
		this.storageSize = storageSize;
		this.partType = partType;
	}

	public synchronized void addPart(T part)  {
		if (parts.size() == storageSize) {
			System.out.println("Storage is full, waiting for update");
			try {
				this.wait();
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
		System.out.println("Storage updated, added a part");
		this.notify();
		parts.add(part);
	}

	public synchronized T getPart()  {
		notifyObservers();
		if (parts.size() == 0) {
			System.out.println("Storage is empty, waiting for update");
			try {
				this.wait();
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
		System.out.println("Storage updated, removed a part");
		this.notify();
		return parts.remove();
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
		for (var i : observers) {
			i.handleEvent();
		}
	}
}
