package com.perforce.akana.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.perforce.akana.qa.exception.TestCodeException;

public class Sleeper {
	
    public enum Unit {
        MILLISECONDS(1),
        SECONDS(1000),
        MINUTES(60000);
        
        private int factor;
        
        private Unit(int factor) {
            this.factor = factor;
        }
    }
    
    private static final Logger logger = LogManager.getLogger(Sleeper.class);
    

    public static void milliseconds(long milliseconds, String reason) {
        sleep((int) milliseconds, Unit.MILLISECONDS, reason);
    }
    
    public static void seconds(int seconds, String reason) {
        sleep(seconds, Unit.SECONDS, reason);
    }
    
    public static void minutes(int minutes, String reason) {
        try {
            logger.trace(String.format("Sleeping %d minutes for %s", minutes, reason));
            int countdown = minutes;
            for (int i = countdown; i > 0; i--) {
                logger.trace(String.format("%d minutes...", i));
                Thread.sleep(Unit.MINUTES.factor);
            }
        } catch (InterruptedException e) {
            throw new TestCodeException(e);
        }
    }
    
    public static void sleep(int increments, Unit units, String reason) {
        try {
            logger.trace(String.format("Sleeping %d %s for %s", increments, units.name().toLowerCase(), reason));
            Thread.sleep(increments * units.factor);
        } catch (InterruptedException e) {
            throw new TestCodeException(e);
        }
    }

}
