package logger.formatter;

import java.time.format.DateTimeFormatter;

import logger.entities.LogMessage;

public class SimpleTextFormatter implements LogFormatter {
    public static final DateTimeFormatter DATE_TIME_FORMATTER=DateTimeFormatter.ofPattern
    ("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public String format(LogMessage logMessage) {
         return String.format("%s [%s] %s - %s: %s\n",
                logMessage.getTimeStamp() .format(DATE_TIME_FORMATTER),
                logMessage.getThreadName(),
                logMessage.getLog(),
                logMessage.getLoggerName(),
                logMessage.getMessage());
    }


}
