package logger.enums;

public enum LogLevel {
    TRACE(1),
    DEBUG(2),
    INFO(3),
    WARN(4),
    ERROR(5);
   
    private final int level;
    LogLevel(int level){
      
        this.level=level;
    }
    public int getLevel(){
        return level;
    }
    public boolean isGreaterOrEqual(LogLevel other) {
        return this.level >= other.level;
    }
}
