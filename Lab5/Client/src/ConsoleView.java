import observer.Observer;
import java.util.Scanner;

public class ConsoleView implements Observer {
	private final Client client;

	public ConsoleView(Client client) {
		this.client = client;
		this.client.addObserver(this);
	}

	public void startView() {
		Scanner in = new Scanner(System.in);
		do {
			String line = in.nextLine();
			client.sendMessage(line);
		} while(client.isActive());
	}

	@Override
	public void handleEvent() {
		System.out.println(client.getMessage());
	}
}
