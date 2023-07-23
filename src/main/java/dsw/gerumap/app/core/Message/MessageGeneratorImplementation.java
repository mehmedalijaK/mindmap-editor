package dsw.gerumap.app.core.Message;

import dsw.gerumap.app.core.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MessageGeneratorImplementation implements IMessageGenerator{

    List<ISubscriber> subscribers;
    String content;
    MessageType type;

    @Override
    public void generate(Message message) {
        this.content = message.getMessage();
        this.type = message.getMessageType();
        notifySubscribers(this);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub==null)
            return;
        if(this.subscribers==null)
            this.subscribers=new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notifications) {
        if(notifications==null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(ISubscriber listener : subscribers){
            listener.update(notifications);
        }
    }
}
