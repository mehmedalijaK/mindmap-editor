package dsw.gerumap.app.core.Logger;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.core.Message.MessageGeneratorImplementation;
import dsw.gerumap.app.core.Message.MessageType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger implements IErrorLogger{


    public StringBuilder sbLog = new StringBuilder("");

    @Override
    public void log(String message) {
        try {
            SimpleDateFormat formatter= new SimpleDateFormat("HHmmss");
            Date date = new Date(System.currentTimeMillis());

            String fileName = "src/main/resources/logs/log-" + formatter.format(date) + ".txt";
            FileWriter myWriter = new FileWriter(fileName);

            myWriter.write(message);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object notification) {
        String content = ((MessageGeneratorImplementation)notification).getContent();
        MessageType type = ((MessageGeneratorImplementation)notification).getType();
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        sbLog.append("[" + type+"]" + "[" +  formatter.format(date) + "] " + content + "\n");
    }
}
