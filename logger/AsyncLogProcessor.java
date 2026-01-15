package logger;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import logger.appender.LogAppender;
import logger.entities.LogMessage;

public class AsyncLogProcessor {
    private final ExecutorService executorService;
    public AsyncLogProcessor(){
        this.executorService=Executors.newSingleThreadExecutor(runnable->{
            Thread thread=new Thread(runnable,"AsyncLogProcessor");
            thread.setDaemon(true);
            return thread;
        });
    }
    public void processor(LogMessage logMessage,List<LogAppender> logAppenders){
        if(executorService.isShutdown()){
            System.err.println("Logger is shutdown, can't process log message");
            return;
        }
        executorService.submit(()->{
            for(LogAppender logAppender:logAppenders){
                logAppender.append(logMessage);
            }
        });
    }
    public void stop(){
        executorService.shutdown();
        try{
            if(executorService.awaitTermination(2, TimeUnit.SECONDS)){
                  System.err.println("Logger executor did not terminate in the specified time.");
                // Forcibly shut down any still-running tasks.
                executorService.shutdownNow();
            }
        }
        catch(InterruptedException e){
            executorService.shutdownNow();

            Thread.currentThread().interrupt();
        }
    }
}
