package production;

import java.util.logging.Logger;

public class Supplier<T> extends Thread {
	private final static Logger logger = Logger.getLogger(Supplier.class.getName());

	private final Storage<T> partsStorage;
	private final Class<T> partType;
	private T part = null;

	public Supplier(Storage<T> partsStorage, Class<T> partType) {
		this.partsStorage = partsStorage;
		this.partType = partType;
	}

	public void createPart() {
		try {
			part = partType.getDeclaredConstructor().newInstance();
		} catch (Exception exception) {
			logger.info("Part couldn't be crated");
		}
		logger.info("Part was created " + part.toString());
	}

	public void sendPartToStorage() throws InterruptedException {
		partsStorage.addPart(part);
		logger.info("Part was sent");
	}

	@Override
	public void run() {
		while (!isInterrupted()) {
			try {
				createPart();
				sendPartToStorage();
				sleep(1000);
			} catch (InterruptedException exception) {
				break;
			}
		}
	}
}
