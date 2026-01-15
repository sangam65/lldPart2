package logger.appender;

public class FileAppender implements LogAppender {

    @Override
    public void formatLog(String msg) {
        System.out.println("Adding it in file");
    }

}
