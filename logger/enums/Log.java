package logger.enums;

public enum Log {
    TRACE("TRACE",1),
    DEBUG("DEBUG",2),
    INFO("INFO",3),
    WARN("WARN",4),
    ERROR("ERROR",5);
    private final String name;
    public String getName() {
        return name;
    }
    private final int level;
    private Log(String name,int level){
        this.name=name;
        this.level=level;
    }
    public int getLevel(){
        return level;
    }
}
