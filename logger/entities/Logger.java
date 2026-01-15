package logger.entities;

import java.util.HashMap;

import logger.appender.ConsoleAppender;
import logger.appender.LogAppender;
import logger.enums.Log;


public class Logger {
    private final static HashMap<String,Logger>logger=new HashMap<>();
    private  Log log ;
    private LogAppender logAppender;
    private Logger(){
        this.log=Log.INFO;
        this.logAppender=new ConsoleAppender();
    }
    public  static Logger getLogger(String className){
        if(!logger.containsKey(className)){
            synchronized(Logger.class){
                logger.put(className,new Logger());
            }
        }
        return logger.get(className);
    }
    public  static Logger getLogger(Class<?> clazz){
        String className=clazz.getName();
        
        return getLogger(className);
    }
    public  void info(String message){
        checkLevelAllowed(Log.INFO,message);
  
    }
    public void warn(String message){
        checkLevelAllowed(Log.WARN, message);
    }
    public void error(String message){
         checkLevelAllowed(Log.WARN,message);
    }
    public void debug(String message){
        checkLevelAllowed(Log.DEBUG,message);
    }
    public void trace(String message){
        checkLevelAllowed(Log.TRACE,message);
    }
    public void changeLevel(Log log) {
        this.log=log;
    }
    private void checkLevelAllowed(Log log,String message){
        if (this.log.getLevel()<=log.getLevel()){
            logAppender.formatLog(message);
          
        }
          
    }
    public  void changeAppender(LogAppender logAppender){
        this.logAppender=logAppender;
    }


}
