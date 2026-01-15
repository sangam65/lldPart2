package logger;

import java.util.HashMap;

import logger.appender.LogAppender;
import logger.entities.Logger;

public class LoggerFactory {
    private static final LoggerFactory loggerFactory = new LoggerFactory();

    private final Logger rootLogger;
    private final AsyncLogProcessor asyncLogProcessor;
    private final HashMap<String, Logger> loggers = new HashMap<>();

    private LoggerFactory() {
        this.asyncLogProcessor = new AsyncLogProcessor();
        this.rootLogger = new Logger("root", null);
        this.loggers.put("root", rootLogger);
    }

    public static LoggerFactory getInsance() {
        return loggerFactory;
    }

    public Logger getLogger(String className) {
        return loggers.computeIfAbsent(className, this::createLogger);
    }

    private Logger createLogger(String name) {
        if (name.equals("root")) {
            return rootLogger;
        }
        int lastDot = name.lastIndexOf('.');
        String parentName = (lastDot == -1) ? "root" : name.substring(0, lastDot);
        Logger parent = getLogger(parentName);
        return new Logger(name, parent);
    }

    public Logger getRootLogger() {
        return rootLogger;
    }

    public AsyncLogProcessor getProcessor() {
        return asyncLogProcessor;
    }   
    public void shutdown() {
        // Stop the processor first to ensure all logs are written.
        asyncLogProcessor.stop();

        // Then, close all appenders.
     
        loggers.values().stream()
                .flatMap(logger -> logger.getAppenderList().stream())
                .distinct()
                .forEach(LogAppender::close);
        System.out.println("Logging framework shut down gracefully.");
    }
}
