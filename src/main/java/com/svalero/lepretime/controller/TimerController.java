package com.svalero.lepretime.controller;

import com.svalero.lepretime.tasks.StopWatchTask;
import com.svalero.lepretime.tasks.TimerTask;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class TimerController implements Initializable {

    private long id;
    public Label lTimer;
    public int seconds;
    public int secondsToFinish;
    public int timeOut;
    public TimerTask timerTask;

    private static final Logger logger = LogManager.getLogger(TimerController.class);


    public TimerController(int seconds, int num, int timeOut) {
        this.seconds = seconds;
        this.id = num;
        this.timeOut = timeOut;
    }

    public long getId() {
        return id;
    }

    public void start(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lTimer.setText(String.valueOf(seconds));
        timerTask = new TimerTask(seconds);
        this.timerTask.messageProperty().addListener((observableValue, oldValue, newValue) -> {
            if(Integer.parseInt(newValue) > 0) {
                this.lTimer.setText(newValue);
            } else {
                this.lTimer.setText(newValue);
                stop();
                this.lTimer.setTextFill(Color.RED);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("El timer" + id + " ha terminado");
                logger.info("Timer" + id + " terminado");
                alert.show();
            }
        });

        new java.util.Timer().schedule(
                new java.util.TimerTask(){
                    @Override
                    public void run(){
                        new Thread(timerTask).start();
                    }
                },
                1000*this.timeOut
        );

        //new Thread(timerTask).start();
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
        this.timerTask.messageProperty().addListener((observableValue, oldValue, newValue) -> {
            if (Integer.parseInt(newValue) > 0) {
                this.lTimer.setText(newValue);
            } else {
                this.lTimer.setText(newValue);
                stop();
                this.lTimer.setTextFill(Color.RED);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("El timer" + id + " ha terminado");
                logger.info("Timer" + id + " terminado");
                alert.show();
            }
        });
        new Thread(timerTask).start();
    }

    @FXML
    public void refresh() {
        stop();
        lTimer.setText(String.valueOf(seconds));
        timerTask = new TimerTask(seconds);
        this.timerTask.messageProperty().addListener((observableValue, oldValue, newValue) -> {
            if(Integer.parseInt(newValue) > 0) {
                this.lTimer.setText(newValue);
            } else {
                this.lTimer.setText(newValue);
                stop();
                this.lTimer.setTextFill(Color.RED);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("El timer" + id + " ha terminado");
                logger.info("Timer" + id + " terminado");
                alert.show();
            }
        });
        new Thread(timerTask).start();
    }


}
