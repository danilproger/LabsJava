import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class ClientHandler implements Runnable {
	private final Socket clientSocket;
	private final Server server;
	private final UUID id;
	private final ObjectOutputStream writer;

	public ClientHandler(Socket clientSocket, Server server) throws IOException {
		this.clientSocket = clientSocket;
		this.server = server;
		writer = new ObjectOutputStream(clientSocket.getOutputStream());
		id = UUID.randomUUID();
	}

	public String getId() {
		return id.toString();
	}

	public void close() throws IOException {
		clientSocket.close();
	}

	public void sendMessage(Message message) throws IOException{
		writer.writeObject(message);
	}

	@Override
	public void run() {
		try {
			ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());

			while (true) {
				Message receivedMessage = (Message) reader.readObject();
				switch (receivedMessage.getType()) {
					case TYPE_COMMAND:
						switch (receivedMessage.getData()){
							case Server.COMMAND_EXIT:
								server.removeClient(this);
								return;
							case Server.COMMAND_USERS_LIST:
								sendMessage(new Message(server.getUsers(), MessageType.TYPE_MESSAGE));
								break;
						}
						break;
					case TYPE_MESSAGE:
						server.sendMessage(new Message(getId() + ": " + receivedMessage.getData(), receivedMessage.getType()));
						break;

				}
				writer.flush();
			}
		} catch (Exception exception) {
			server.removeClient(this);
		}
	}
}
