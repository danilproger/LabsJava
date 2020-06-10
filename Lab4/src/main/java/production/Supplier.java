package production;

public class Supplier<T> extends Thread {
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
			System.out.println("part couldn't be crated");
		}
		System.out.println("part was created");
	}

	public void sendPartToStorage() throws InterruptedException {
		partsStorage.addPart(part);
		System.out.println("part was sent");
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
