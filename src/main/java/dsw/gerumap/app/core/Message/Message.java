package dsw.gerumap.app.core.Message;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Message {

    private String message;
    private MessageType messageType;

    public Message(String message, MessageType messageType) {
        this.message = message;
        this.messageType = messageType;
    }
}
