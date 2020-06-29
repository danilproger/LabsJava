package production;

import parts.Car;

import java.util.logging.Logger;

public class Dealer extends Thread {
	private final static Logger logger = Logger.getLogger(Dealer.class.getName());

	public final Storage<Car> carStorage;

	public Dealer(Storage<Car> carStorage) {
		this.carStorage = carStorage;
	}

	public void buyCar() throws InterruptedException{
		carStorage.getPart();
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				buyCar();
				logger.info("Car was bought");
				sleep(1000);
			} catch (InterruptedException exception) {
				break;
			}
		}
	}
}
