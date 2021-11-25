package com.example.FrontEnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class startApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(startApplication.class.getResource("start.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(fxmlLoader.load(), 938, 675);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}