package production;

import parts.*;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Factory {
	private final Supplier<Body> bodySupplier;
	private final Supplier<Engine> engineSupplier;
	private final ArrayList<Supplier<Accessory>> accessorySuppliers;

	private final ArrayList<Dealer> dealers;

	private final ThreadPoolExecutor workers;

	public Factory() {
		Storage<Body> bodyStorage = new Storage<>(10, Body.class);
		Storage<Engine> engineStorage = new Storage<>(10, Engine.class);
		Storage<Accessory> accessoryStorage = new Storage<>(10, Accessory.class);
		Storage<Car> carStorage = new Storage<>(5, Car.class);

		this.bodySupplier = new Supplier<>(bodyStorage, Body.class);
		this.engineSupplier =  new Supplier<>(engineStorage, Engine.class);
		this.accessorySuppliers = new ArrayList<>();
		for (int i = 0; i < 4; ++i) {
			accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class));
		}

		this.dealers = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			dealers.add(new Dealer(carStorage));
		}

		this.workers = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

		carStorage.addObserver(new CarController(
				bodyStorage,
				engineStorage,
				accessoryStorage,
				carStorage,
				workers
		));

	}

	public void startProduction() {
		bodySupplier.start();
		engineSupplier.start();
		for (var i : accessorySuppliers) {
			i.start();
		}
		for (var i : dealers) {
			i.start();
		}
	}

	public void stopProduction() throws InterruptedException {
		bodySupplier.join();
		engineSupplier.join();

		for (var i : accessorySuppliers) {
			i.join();
		}

		for (var i : dealers) {
			i.join();
		}

		workers.shutdownNow();

		bodySupplier.interrupt();
		engineSupplier.interrupt();
		for (var i : accessorySuppliers) {
			i.interrupt();
		}

		for (var i : dealers) {
			i.interrupt();
		}
		workers.awaitTermination(100, TimeUnit.NANOSECONDS);
	}
}
