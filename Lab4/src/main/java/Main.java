import parts.*;
import production.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
	public static void main(String[] args) throws Exception {
		Storage<Body> bodyStorage = new Storage<>(10, Body.class);
		Storage<Engine> engineStorage = new Storage<>(10, Engine.class);
		Storage<Accessory> accessoryStorage = new Storage<>(10, Accessory.class);
		Storage<Car> carStorage = new Storage<>(5, Car.class);

		Supplier<Body> bodySupplier = new Supplier<>(bodyStorage, Body.class);
		Supplier<Engine> engineSupplier = new Supplier<>(engineStorage, Engine.class);
		ArrayList<Supplier<Accessory>> accessorySuppliers = new ArrayList<>();

		for (int i = 0; i < 4; ++i) {
			accessorySuppliers.add(new Supplier<>(accessoryStorage, Accessory.class));
		}

		ArrayList<Dealer> dealers = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			dealers.add(new Dealer(carStorage));
		}

		ExecutorService workers = Executors.newFixedThreadPool(5);

		Factory factory = new Factory(
				bodyStorage,
				engineStorage,
				accessoryStorage,
				carStorage,
				workers
		);

		carStorage.addObserver(factory);
		Production production = new Production(
				bodySupplier,
				engineSupplier,
				accessorySuppliers,
				dealers
		);
		production.startProduction();
	}
}
