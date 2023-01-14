package com.svalero.lepretime.controller;

import com.svalero.lepretime.tasks.ClockTask;
import com.svalero.lepretime.tasks.StopWatchTask;
import com.svalero.lepretime.tasks.TimerTask;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class StopWatchController implements Initializable {

    public int id;
    public int currentSeconds;
    public int timeOut;
    public Label lStopWatch;

    public StopWatchTask stopWatchTask;

    public StopWatchController(int num, int timeOut) {
        id = num;
        this.timeOut = timeOut;
    }

    public int getId() {
        return id;
    }

    public void start(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String hourFormat = "";
        String minFormat = "";
        lStopWatch.setText("0");

        stopWatchTask = new StopWatchTask(0);
        this.stopWatchTask.messageProperty().addListener((observableValue, oldValue, newValue) -> {
            lStopWatch.setText(newValue);
        });
            new java.util.Timer().schedule(
                    new java.util.TimerTask(){
                        @Override
                        public void run(){
                            new Thread(stopWatchTask).start();
                        }
                    },
                    1000*this.timeOut
            );

        //new Thread(stopWatchTask).start();
    }

    @FXML
    public void stop(javafx.event.ActionEvent event) {
        stop();
    }

    public void stop() {
        currentSeconds = Integer.parseInt(lStopWatch.getText());
        stopWatchTask.cancel();
    }
    @FXML
    public void reanudar() {
        stopWatchTask = new StopWatchTask(currentSeconds);
        this.stopWatchTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lStopWatch.setText(newValue));
        new Thread(stopWatchTask).start();
    }

    @FXML
    public void refresh() {
        stop();
        lStopWatch.setText("0");
        stopWatchTask = new StopWatchTask();
        this.stopWatchTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lStopWatch.setText(newValue));
        new Thread(stopWatchTask).start();
    }
}
