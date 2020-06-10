package production;

import parts.Car;

public class Dealer extends Thread {
	public final Storage<Car> carStorage;

	public Dealer(Storage<Car> carStorage) {
		this.carStorage = carStorage;
	}

	public void buyCar() {
		carStorage.getPart();
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				buyCar();
				System.out.println("car was bought");
				sleep(1000);
			} catch (InterruptedException exception) {
				break;
			}
		}
	}
}
