package com.svalero.lepretime.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogsController implements Initializable {

    public TextArea taLog;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taLog.setText(readLog());
    }


    public String readLog() {

        String texto = "";

        try {
            FileReader entrada = new FileReader("timer.log");

            int c = 0;

            while(c != -1) {
                c = entrada.read();

                char letra = (char)c;

                texto += letra;
            }

            entrada.close();

            System.out.println(texto);

        } catch (IOException e) {

            System.out.println("No se ha encontrado el archivo");
        }

        return texto;
    }
}
