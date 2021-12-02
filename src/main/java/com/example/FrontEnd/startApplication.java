package com.example.FrontEnd;

import com.example.BackEnd.Facade;
import com.example.easyGoBetween.frontEndTalkToObjects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class startApplication extends Application {
    public static frontEndTalkToObjects goBetween = new frontEndTalkToObjects();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(startApplication.class.getResource("start.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(fxmlLoader.load(), 938, 675);
        stage.setScene(scene);
        stage.show();
    }

    public static Facade facade;
    public static void main(String[] args) {
        facade = new Facade();
        launch();
    }
}