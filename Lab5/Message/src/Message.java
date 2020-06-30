import java.io.Serializable;

public class Message implements Serializable {
	private String data;
	private MessageType type;

	public Message(String data, MessageType type) {
		this.data = data;
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
}
