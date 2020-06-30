public class ClientMain {
	private static final String SERVER_IP = "127.0.0.1";
	private static final int SERVER_PORT = 1337;

	public static void main(String[] args) {
		try {
			Client client = new Client(SERVER_IP, SERVER_PORT);
			Thread thread = new Thread(client);
			ConsoleView view = new ConsoleView(client);
			thread.start();
			view.startView();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
