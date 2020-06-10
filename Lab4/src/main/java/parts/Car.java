package parts;

import java.util.ArrayList;

public class Car extends Part {
	private final Body body;
	private final Engine engine;
	private final ArrayList<Accessory> accessories;

	public Car(Body body, Engine engine, ArrayList<Accessory> accessories) {
		this.body = body;
		this.engine = engine;
		this.accessories = accessories;
	}

	public String getCharacteristic() {
		StringBuilder builder = new StringBuilder();
		builder
				.append("Car:")
				.append(" ")
				.append(this.getID())
				.append("\n")
				.append("Body:")
				.append(" ")
				.append(body.getID())
				.append("\n")
				.append("Engine:")
				.append(" ")
				.append(engine.getID())
				.append("\n")
				.append("Accessories:")
				.append("\n");

		for (var i : accessories) {
			builder
					.append("	")
					.append(i.getID())
					.append("\n");
		}

		return builder.toString();
	}
}
