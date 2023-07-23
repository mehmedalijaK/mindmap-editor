package dsw.gerumap.app.core.Logger;

import dsw.gerumap.app.core.Message.MessageGeneratorImplementation;
import dsw.gerumap.app.core.Message.MessageType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleLogger implements IErrorLogger{

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public ConsoleLogger(){

    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void update(Object notification) {
        String content = ((MessageGeneratorImplementation)notification).getContent();
        MessageType type = ((MessageGeneratorImplementation)notification).getType();
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        if(type.equals(MessageType.ERROR))
            log( ANSI_RED + "[" + type+"]" + ANSI_RESET + "[" +  formatter.format(date) + "] " + content);
        else if(type.equals(MessageType.INFORMATION))
            log( ANSI_GREEN + "[" + type+"]" + ANSI_RESET + "[" +  formatter.format(date) + "] " + content);
        else
            log( ANSI_YELLOW + "[" + type+"]" + ANSI_RESET + "[" +  formatter.format(date) + "] " + content);
    }
}
