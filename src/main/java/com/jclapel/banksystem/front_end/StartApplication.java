package com.jclapel.banksystem.front_end;

import com.jclapel.banksystem.back_end.Facade;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StartApplication extends Application { // Albin worked on this
    /*

	Application starting class, starts the application.

	Main Contributor(s): Albin
	Contributor(s): 

	*/

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