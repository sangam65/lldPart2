package logger.entities;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


import logger.appender.LogAppender;
import logger.enums.LogLevel;


public class Logger {
    private final String name;
    private LogLevel logLevel  ;
    private final Logger parent;

    public Logger getParent() {
        return parent;
    }
    private boolean additivity;
    private List<LogAppender> appenderList;
   
    public LogLevel getLogLevel() {
        return logLevel;
    }
    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
    public boolean isAdditivity() {
        return additivity;
    }
    public void setAdditivity(boolean additivity) {
        this.additivity = additivity;
    }
    public List<LogAppender> getAppenderList() {
        return appenderList;
    }
    public void setAppenderList(List<LogAppender> appenderList) {
        this.appenderList = appenderList;
    }
    public Logger(String name, Logger parent) {
        this.name = name;
        this.logLevel=LogLevel.INFO;
        this.parent = parent;
        this.additivity = false;
        this.appenderList = new CopyOnWriteArrayList<>();
    }
  
    public  void info(String message){
        checkLevelAllowed(LogLevel.INFO,message);
  
    }
    public void warn(String message){
        checkLevelAllowed(LogLevel.WARN, message);
    }
    public void error(String message){
         checkLevelAllowed(LogLevel.ERROR,message);
    }
    public void debug(String message){
        checkLevelAllowed(LogLevel.DEBUG,message);
    }
    public void trace(String message){
        checkLevelAllowed(LogLevel.TRACE,message);
    }
    public void changeLevel(LogLevel log) {
        this.logLevel=log;
    }
    public LogLevel getLevel(){
      return this.logLevel;

    }
    private void checkLevelAllowed(LogLevel log,String message){
       if( this.logLevel.isGreaterOrEqual(log)){

            callAppenders(new LogMessage(log, name, message));
       }
          
    }
    private void callAppenders(LogMessage logMessage){
        for(LogAppender appender:appenderList){
            appender.append(logMessage);
        }
    }
    public  void addAppender(LogAppender logAppender){
        this.appenderList.add(logAppender);
    }


}
