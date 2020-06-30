import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server implements Runnable {
	public static final String COMMAND_EXIT = "-exit";
	public static final String COMMAND_USERS_LIST = "-l";

	private final ServerSocket serverSocket;
	private final List<ClientHandler> clients;

	public Server(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
		clients = Collections.synchronizedList(new LinkedList<>());
	}

	@Override
	public void run() {
		while (true) {
			Socket clientSocket;
			try {
				clientSocket = serverSocket.accept();
				ClientHandler handler = new ClientHandler(clientSocket, this);
				Thread clientThread = new Thread(handler);
				clientThread.start();
				System.out.println("Клиент добавлен, ip: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort() + " " + handler.getId());
				addClient(handler);
				sendMessage(new Message("Клиент " + handler.getId() + " вошел в чат", MessageType.TYPE_MESSAGE));
			} catch (IOException ex) {
				close();
				return;
			}
		}
	}

	public void sendMessage(Message message) {
		for (var client : clients) {
			try {
				client.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getUsers() {
		StringBuilder users = new StringBuilder();
		users.append("[\n");
		for (var client : clients) {
			users
					.append(client.getId())
					.append(",\n");
		}
		users.append("]\n");
		return users.toString();
	}

	public void close() {
		if (serverSocket != null) {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (var client : clients) {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addClient(ClientHandler client) {
		clients.add(client);
	}

	public void removeClient(ClientHandler client) {
		System.out.println("Клиент " + client.getId() + " удален");
		sendMessage(new Message("Клиент " + client.getId() + " ушел из чата", MessageType.TYPE_MESSAGE));
		clients.remove(client);
	}
}
