package com.svalero.lepretime.controller;


import com.svalero.lepretime.tasks.ClockTask;
import com.svalero.lepretime.util.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    public int numStopWatch;
    public int numTimer;
    public Text tClock;

    public TextField tfTimer;
    public TextField tfTimeOut;

    public TabPane tabPane;

    public StopWatchController stopWatchController;
    public TimerController timerController;
    public LogsController logsController;

    public ClockTask clockTask;

    public AppController() {
    }

    public void launchStopWatch() {
        try {
            numStopWatch++;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("stopWatch.fxml"));


            stopWatchController = new StopWatchController(numStopWatch, Integer.parseInt(tfTimeOut.getText()));
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
            numTimer++;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("timer.fxml"));

            timerController = new TimerController(Integer.parseInt(tfTimer.getText()), numTimer, Integer.parseInt(tfTimeOut.getText()));
            loader.setController(timerController);
            VBox timerBox = loader.load();

            Tab tab = new Tab("Timer " + timerController.getId(), timerBox);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void showLog() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI("showLog.fxml"));

            logsController = new LogsController();
            loader.setController(logsController);
            VBox logBox = loader.load();

            Tab tab = new Tab("Log", logBox);
            tabPane.getTabs().add(tab);
            tabPane.getSelectionModel().select(tab);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @FXML
    public void openLog(ActionEvent event) {
        try
        {
            File file = new File("C:\\Users\\campo\\IdeaProjects\\MultidescargaFx-v2\\MultidescargaFx-v2\\multidescargas.log");
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("Recurso no soportado");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())
                desktop.open(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clockTask = new ClockTask();
        this.clockTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.tClock.setText(newValue));
        new Thread(clockTask).start();
        tfTimeOut.setText("0");
    }
}
