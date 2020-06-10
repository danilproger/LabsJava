package parts;

import java.util.UUID;

public abstract class Part {
	private final UUID id;

	public Part() {
		id = UUID.randomUUID();
	}

	public String getID() {
		return id.toString();
	}
}
