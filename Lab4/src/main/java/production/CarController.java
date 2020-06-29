package production;

import observer.Observer;
import parts.Accessory;
import parts.Body;
import parts.Car;
import parts.Engine;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class CarController implements Observer {
	private final static Logger logger = Logger.getLogger(CarController.class.getName());

	private final Storage<Body> bodyStorage;
	private final Storage<Engine> engineStorage;
	private final Storage<Accessory> accessoryStorage;
	private final Storage<Car> carStorage;
	private final ThreadPoolExecutor workers;

	public CarController(Storage<Body> bodyStorage,
						 Storage<Engine> engineStorage,
						 Storage<Accessory> accessoryStorage,
						 Storage<Car> carStorage,
						 ThreadPoolExecutor workers) {
		this.bodyStorage = bodyStorage;
		this.engineStorage = engineStorage;
		this.accessoryStorage = accessoryStorage;
		this.carStorage = carStorage;
		this.workers = workers;
		startTask();
	}

	public synchronized Car createCar() throws InterruptedException{
		Body body = bodyStorage.getPart();
		Engine engine = engineStorage.getPart();
		ArrayList<Accessory> accessories = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			accessories.add(accessoryStorage.getPart());
		}

		Car newCar = new Car(body, engine, accessories);
		logger.info("Car was created " + newCar.getCharacteristic());
		return newCar;
	}

	@Override
	public void handleEvent() {
		long size = carStorage.getStorageSize();
		long capacity = carStorage.getStorageCapacity();
		long inProgress = workers.getTaskCount() - workers.getCompletedTaskCount();

		if (size + inProgress < capacity/3) {
			for (int i = 0; i < capacity/3; i++) {
				startTask();
			}
		}
		startTask();
	}

	private void startTask() {
		workers.execute(() -> {
			Car car;
			try {
				car = createCar();
				carStorage.addPart(car);
			} catch (InterruptedException exception) {
				Thread.currentThread().interrupt();
			}
		});
	}
}
