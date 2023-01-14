package com.svalero.lepretime.controller;

import com.svalero.lepretime.tasks.ClockTask;
import com.svalero.lepretime.tasks.StopWatchTask;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class StopWatchController implements Initializable {

    public Label lStopWatch;

    public StopWatchTask stopWatchTask;

    public void start(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stopWatchTask = new StopWatchTask();
        this.stopWatchTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lStopWatch.setText(newValue));
        new Thread(stopWatchTask).start();
    }
}
