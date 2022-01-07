package com.example.FrontEnd;

import com.example.BackEnd.Facade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartApplication extends Application { // Albin worked on this
    public static Facade facade = new Facade();//This is where that yellow warning signs coming from??
    public static int securityKey1 = 11111;
    public static int securityKey2 = 10101;
    public static int securityKey3 = 20202;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("start.fxml"));
        //stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(fxmlLoader.load(), 938, 675);
        stage.setScene(scene);
        stage.setTitle("jc.Lapel bank");
//        stage.getIcons().add(new Image("com/example/FrontEnd/image/lapel.png"));
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}