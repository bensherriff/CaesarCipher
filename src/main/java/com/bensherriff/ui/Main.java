package com.bensherriff.ui;

import com.bensherriff.app.Reader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Main extends Application {

    private final static Logger logger = LogManager.getLogger(Main.class.getName());

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(
                getClass().getClassLoader().getResource("main.fxml")));
        stage.setTitle("Caesar Cipher");

        Reader.loadProperties("data.properties");

        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
