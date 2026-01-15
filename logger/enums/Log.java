package logger.enums;

public enum Log {
    TRACE(1),
    DEBUG(2),
    INFO(3),
    WARN(4),
    ERROR(5);
    private final int level;
    private Log(int level){
        this.level=level;
    }
    public int getLevel(){
        return level;
    }
}
