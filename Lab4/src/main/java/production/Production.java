package production;

import parts.*;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Production {
	private final Supplier<Body> bodySupplier;
	private final Supplier<Engine> engineSupplier;
	private final ArrayList<Supplier<Accessory>> accessorySuppliers;

	private final ArrayList<Dealer> dealers;

	public Production(Supplier<Body> bodySupplier,
					  Supplier<Engine> engineSupplier,
					  ArrayList<Supplier<Accessory>> accessorySuppliers,
					  ArrayList<Dealer> dealers) {
		this.bodySupplier = bodySupplier;
		this.engineSupplier = engineSupplier;
		this.accessorySuppliers = accessorySuppliers;
		this.dealers = dealers;
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

	public void interruptProduction() {
		bodySupplier.interrupt();
		engineSupplier.interrupt();
		for (var i : accessorySuppliers) {
			i.interrupt();
		}
		for (var i : dealers) {
			i.interrupt();
		}
	}
}
