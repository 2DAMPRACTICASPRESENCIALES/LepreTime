package com.svalero.lepretime.controller;

import com.svalero.lepretime.tasks.StopWatchTask;
import com.svalero.lepretime.tasks.TimerTask;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class TimerController implements Initializable {

    public Label lTimer;
    public int seconds;
    public int secondsToFinish;
    public TimerTask timerTask;


    public TimerController(int seconds) {
        this.seconds = seconds;
    }

    public void start(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timerTask = new TimerTask(seconds);
        this.timerTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lTimer.setText(newValue));
        new Thread(timerTask).start();
    }

    @FXML
    public void stop(javafx.event.ActionEvent event) {
        stop();
    }

    public void stop() {
        secondsToFinish = Integer.parseInt(lTimer.getText());
        timerTask.cancel();
    }
    @FXML
    public void reanudar() {
        timerTask = new TimerTask(secondsToFinish);
        this.timerTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lTimer.setText(newValue));
        new Thread(timerTask).start();
    }


}
