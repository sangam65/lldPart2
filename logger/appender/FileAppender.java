package logger.appender
;




import logger.entities.LogMessage;
import logger.formatter.LogFormatter;

public class FileAppender implements LogAppender {

    @Override
    public void append(LogMessage logMessage) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'append'");
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'close'");
    }

    @Override
    public LogFormatter getFormatter() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFormatter'");
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFormatter'");
    }
    
   

}
