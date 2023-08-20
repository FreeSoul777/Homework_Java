package com.example.cipheraesxorrsa;

import com.example.cipheraesxorrsa.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CAXRApplication extends Application {

    static MainController mainController;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(CAXRApplication.class.getResource("sde-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Secure Document Exchange");
        stage.setScene(scene);
        mainController = new MainController(scene.getWindow());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}