package dsw.gerumap.app.core.Logger;

import dsw.gerumap.app.core.observer.ISubscriber;

public interface IErrorLogger extends ISubscriber {
    void log(String message);
}
