package com.example.FrontEnd;

import com.example.BackEnd.Facade;
import com.example.TestingInterface.TestingInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StartApplication extends Application {
    public static TestingInterface goBetween = new TestingInterface();//Get rid of later when no longer needed

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("start.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(fxmlLoader.load(), 938, 675);
        stage.setScene(scene);
        stage.show();
    }

    public static Facade facade;
    public static void main(String[] args) {
        launch();
        facade = new Facade();
    }
}