package logger.appender;



import logger.entities.LogMessage;
import logger.formatter.LogFormatter;
import logger.formatter.SimpleTextFormatter;

public class ConsoleAppender implements LogAppender{
    private LogFormatter logFormatter;
    public ConsoleAppender(){
        this.logFormatter=new SimpleTextFormatter();
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(logFormatter.format(logMessage));
        
    }

    @Override
    public void close() {
        
        
    }

    @Override
    public LogFormatter getFormatter() {
        return logFormatter;
    }

    @Override
    public void setFormatter(LogFormatter logFormatter) {
        this.logFormatter=logFormatter;
        
    }

    

}
