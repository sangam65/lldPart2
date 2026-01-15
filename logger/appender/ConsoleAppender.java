package logger.appender;

import java.time.LocalDateTime;

public class ConsoleAppender implements LogAppender{

    @Override
    public void formatLog(String level,String msg) {
       System.out.println(LocalDateTime.now()+" "+level+" "+ msg);
    }

}
