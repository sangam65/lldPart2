package logger.formatter;

import logger.entities.LogMessage;

public interface LogFormatter {
    String format(LogMessage logMessage);
}
