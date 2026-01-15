package logger;

import logger.entities.Logger;

public class LogDemo {
    public static void main(String []args){
        Logger logger=Logger.getLogger("Student.class");
        logger.info("sangam");
        logger.debug("debug");
    }
}
