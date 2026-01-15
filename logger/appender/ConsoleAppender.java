package logger.appender;

public class ConsoleAppender implements LogAppender{

    @Override
    public void formatLog(String msg) {
       System.out.println(msg);
    }

}
