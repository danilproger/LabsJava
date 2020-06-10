package production;

import observer.Observer;
import parts.Accessory;
import parts.Body;
import parts.Car;
import parts.Engine;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class Factory implements Observer {
	private final Storage<Body> bodyStorage;
	private final Storage<Engine> engineStorage;
	private final Storage<Accessory> accessoryStorage;
	private final Storage<Car> carStorage;
	private final ExecutorService workers;

	public Factory(Storage<Body> bodyStorage,
				   Storage<Engine> engineStorage,
				   Storage<Accessory> accessoryStorage,
				   Storage<Car> carStorage,
				   ExecutorService workers) {
		this.bodyStorage = bodyStorage;
		this.engineStorage = engineStorage;
		this.accessoryStorage = accessoryStorage;
		this.carStorage = carStorage;
		this.workers = workers;
	}

	public synchronized Car createCar() {
		Body body = bodyStorage.getPart();
		Engine engine = engineStorage.getPart();
		ArrayList<Accessory> accessories = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			accessories.add(accessoryStorage.getPart());
		}

		Car newCar = new Car(body, engine, accessories);
		System.out.println("Car was created");
		return newCar;
	}

	@Override
	public void handleEvent() {
		workers.execute(new Runnable() {
			@Override
			public void run() {
				Car car = createCar();
				carStorage.addPart(car);
			}
		});
	}
}
