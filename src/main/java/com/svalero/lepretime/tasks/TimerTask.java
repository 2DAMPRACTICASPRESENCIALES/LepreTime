package com.svalero.lepretime.tasks;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimerTask extends Task<Integer> {


    private int seconds;
    private static final Logger logger = LogManager.getLogger(java.util.TimerTask.class);

    public TimerTask(int seconds) {
        this.seconds = seconds;
    }

    @Override
    protected Integer call() throws Exception {
        logger.trace("Timer iniciada");

        while (true){
            Thread.sleep(1000);
            seconds--;
            updateMessage(String.valueOf(seconds));
        }

    }
}
