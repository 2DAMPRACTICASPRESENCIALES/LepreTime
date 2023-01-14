package com.svalero.lepretime.tasks;

import javafx.concurrent.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimerTask;

public class ClockTask extends Task<Integer> {

    private DateTimeFormatter dtf;
    private static final Logger logger = LogManager.getLogger(TimerTask.class);

    public ClockTask() {
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    @Override
    protected Integer call() throws Exception {
        logger.trace("Reloj iniciada");

        while (true){
            Thread.sleep(1000);
            LocalDateTime now = LocalDateTime.now();
            updateMessage(dtf.format(now));
        }
    }
}
