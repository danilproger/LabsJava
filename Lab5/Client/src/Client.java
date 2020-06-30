import observer.Observable;
import observer.Observer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client implements Runnable, Observable {
	public static final String COMMAND_EXIT = "-exit";

	private final List<Observer> observers;
	private final Socket serverSocket;
	private Message message;
	private boolean isActive;

	private final ObjectOutputStream writer;
	private final ObjectInputStream reader;

	public Client(String ip, int port) throws IOException {
		serverSocket = new Socket(ip, port);
		observers = new ArrayList<>();
		this.writer = new ObjectOutputStream(serverSocket.getOutputStream());
		this.reader = new ObjectInputStream(serverSocket.getInputStream());
		isActive = true;
	}

	public void sendMessage(String line) {
		try {
			if (line.startsWith("-")) {
				if (line.equals(COMMAND_EXIT)) {
					isActive = false;
					writer.writeObject(new Message(line, MessageType.TYPE_COMMAND));
					writer.flush();
					serverSocket.close();
				}
			} else {
				writer.writeObject(new Message(line, MessageType.TYPE_MESSAGE));
				writer.flush();
			}
		} catch (IOException ex) {
			isActive = false;
			ex.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				message = (Message) reader.readObject();
				notifyObservers();
			} catch (Exception ex) {
				isActive = false;
				return;
			}
		}

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
		for (var observer : observers) {
			observer.handleEvent();
		}
	}

	public boolean isActive() {
		return isActive;
	}

	public String getMessage() {
		return message.getData();
	}
}
