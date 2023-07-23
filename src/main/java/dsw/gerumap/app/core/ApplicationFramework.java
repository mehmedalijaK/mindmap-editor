package dsw.gerumap.app.core;

import dsw.gerumap.app.core.Logger.IErrorLogger;
import dsw.gerumap.app.core.Message.MessageGeneratorImplementation;
import dsw.gerumap.app.gui.swing.view.GuiMessage;
import dsw.gerumap.app.serializer.GsonSerializer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicationFramework {

    protected Gui gui;
    protected MapRepository mapRepository;
    protected IErrorLogger errorLogger;
    protected MessageGeneratorImplementation messageObserver;
    protected GuiMessage guiMessage;
    protected Serializer serializer;

    public void run() {
        this.gui.start();
    }

    public void initialise(Gui gui, MapRepository mapRepository, IErrorLogger errorLogger, MessageGeneratorImplementation messageObserver, GuiMessage guiMessage, Serializer serializer) {
        this.gui = gui;
        this.mapRepository = mapRepository;
        this.errorLogger = errorLogger;
        this.messageObserver = messageObserver;
        this.guiMessage = guiMessage;
        this.serializer = serializer;
    }
    // Singleton
    private static ApplicationFramework instance;

    private ApplicationFramework() {

    }

    public static ApplicationFramework getInstance() {
        if (instance == null) {
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public Serializer getSerializer() {
        return serializer;
    }

}
