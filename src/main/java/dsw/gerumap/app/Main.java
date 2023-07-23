package dsw.gerumap.app;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Gui;
import dsw.gerumap.app.core.Logger.ConsoleLogger;
import dsw.gerumap.app.core.Logger.FileLogger;
import dsw.gerumap.app.core.Logger.IErrorLogger;
import dsw.gerumap.app.core.MapRepository;
import dsw.gerumap.app.core.Message.MessageGeneratorImplementation;
import dsw.gerumap.app.core.Serializer;
import dsw.gerumap.app.gui.swing.SwingGui;
import dsw.gerumap.app.gui.swing.view.GuiMessage;
import dsw.gerumap.app.repository.MapRepositoryImpl;
import dsw.gerumap.app.serializer.GsonSerializer;


public class Main {

    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        Gui gui = new SwingGui();
        MapRepository mapRepository = new MapRepositoryImpl();
        MessageGeneratorImplementation messageObserver = new MessageGeneratorImplementation();
        IErrorLogger errorLogger = new ConsoleLogger();
        GuiMessage guiMessage = new GuiMessage();
        Serializer serializer = new GsonSerializer();
        messageObserver.addSubscriber(errorLogger);
        messageObserver.addSubscriber(guiMessage);
        appCore.initialise(gui, mapRepository, errorLogger, messageObserver, guiMessage, serializer);
        appCore.run();
    }




}
