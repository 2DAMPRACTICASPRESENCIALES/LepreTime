package com.svalero.lepretime.controller;


import com.svalero.lepretime.tasks.ClockTask;
import com.svalero.lepretime.util.R;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    public Text tClock;

    public TextField tfTimer;

    public TabPane tabPane;

    public StopWatchController stopWatchController;
    public TimerController timerController;

    public ClockTask clockTask;

    public AppController() {
    }

    public void launchStopWatch() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("stopWatch.fxml"));


            stopWatchController = new StopWatchController();
            loader.setController(stopWatchController);
            VBox stopWatchBox = loader.load();

            Tab tab = new Tab("Crono " + stopWatchController.getId(), stopWatchBox);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void launchTimer() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("timer.fxml"));


            timerController = new TimerController(Integer.parseInt(tfTimer.getText()));
            loader.setController(timerController);
            VBox stopWatchBox = loader.load();

            tabPane.getTabs().add(new Tab("Timer " + timerController.getId(), stopWatchBox));

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clockTask = new ClockTask();
        this.clockTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.tClock.setText(newValue));
        new Thread(clockTask).start();
    }
}
