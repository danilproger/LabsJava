import java.io.IOException;
import java.util.Scanner;

public class ServerMain {
	private static final int SERVER_PORT = 1337;
	private static final String SERVER_STOP = "-stop";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Server server;
		try {
			server = new Server(SERVER_PORT);
		} catch (IOException ex) {
			ex.printStackTrace();
			return;
		}
		Thread thread = new Thread(server);
		thread.start();

		if (in.nextLine().equals(SERVER_STOP)) {
			server.close();
		}
	}
}
