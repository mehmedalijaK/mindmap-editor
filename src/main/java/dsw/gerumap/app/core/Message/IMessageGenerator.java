package dsw.gerumap.app.core.Message;

import dsw.gerumap.app.core.observer.IPublisher;

public interface IMessageGenerator extends IPublisher {
    void generate(Message message);
}
