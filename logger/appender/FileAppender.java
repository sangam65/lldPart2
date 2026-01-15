package logger.appender;


import java.time.LocalDateTime;

public class FileAppender implements LogAppender {
    
    @Override
    public void formatLog(String level,String msg) {
       System.out.println(LocalDateTime.now()+" will be added in file "+level+" "+ msg);
      
    }


}
