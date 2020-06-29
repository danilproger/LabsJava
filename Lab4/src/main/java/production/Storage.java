package production;

import observer.Observable;
import observer.Observer;
import parts.Part;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.logging.Logger;

public class Storage<T> implements Observable {
	private final ArrayList<Observer> observers;
	private final Queue<T> parts;
	private final int storageCapacity;
	private final Class<T> partType;

	private final static Logger logger = Logger.getLogger(Storage.class.getName());

	public Storage(int storageSize, Class<T> partType) {
		observers = new ArrayList<>();
		this.parts = new ArrayDeque<>();
		this.storageCapacity = storageSize;
		this.partType = partType;
	}

	public synchronized void addPart(T part) throws InterruptedException{
		while (parts.size() == storageCapacity) {
			logger.info("Storage is full, waiting for update");
			this.wait();
		}
		logger.info("Storage updated, added a part " + partType + " " + part.toString());
		this.notify();
		parts.add(part);
	}

	public synchronized T getPart() throws InterruptedException {
		while (parts.size() == 0) {
			logger.info("Storage is empty, waiting for update");
			this.wait();
		}
		T part = parts.remove();
		logger.info("Storage updated, removed a part " + partType + " " + part.toString());
		this.notify();
		notifyObservers();
		return part;
	}

	public synchronized int getStorageCapacity() {
		return storageCapacity;
	}

	public synchronized int getStorageSize() {
		return parts.size();
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
