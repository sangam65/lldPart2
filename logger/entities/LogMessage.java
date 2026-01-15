package logger.entities;

import java.time.LocalDateTime;

import logger.enums.LogLevel;

public final class LogMessage {
    private final LocalDateTime timeStamp;
    private final LogLevel log;
    private final String loggerName;
    private final String message;
    private final String threadName;
    public LogMessage( LogLevel log, String loggerName, String message) {
        this.timeStamp = LocalDateTime.now();
        this.log = log;
        this.loggerName = loggerName;
        this.message = message;
        this.threadName = Thread.currentThread().getName();
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public LogLevel getLog() {
        return log;
    }
    public String getLoggerName() {
        return loggerName;
    }
    public String getMessage() {
        return message;
    }
    public String getThreadName() {
        return threadName;
    }



}
