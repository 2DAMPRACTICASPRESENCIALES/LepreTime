package com.svalero.lepretime.tasks;

import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.TimerTask;

public class StopWatchTask extends Task<Integer> {
    private int seconds;
    private static final Logger logger = LogManager.getLogger(TimerTask.class);

    public StopWatchTask() {
    }

    @Override
    protected Integer call() throws Exception {
        logger.trace("Cronometro iniciada");

        while (true){
            Thread.sleep(1000);
            seconds++;
            updateMessage(String.valueOf(seconds));
        }
    }

}
