package hu.dpc.edu;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vrg on 2016. 11. 11..
 */
public class MyJava8Logger implements Java8Logger {
    private Logger logger;

    public MyJava8Logger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(Level level, MessageProducer messageProducer) {
        if(logger.isLoggable(level)) {
            final String message = messageProducer.produceMessage();
            logger.log(level, message);
        }
    }
}
