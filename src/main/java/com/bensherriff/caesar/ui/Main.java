package com.bensherriff.caesar.ui;

import com.bensherriff.caesar.app.Hacker;
import com.bensherriff.caesar.app.Util;
import com.bensherriff.caesar.data.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Main extends Application {

    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(
                getClass().getClassLoader().getResource("main.fxml")));
        Util.loadProperties("project.properties");

        Data data = (Data) Util.deserialize("data.xml", Data.class);
        Hacker.getInstance().setData(data);

        stage.setTitle("Caesar Cipher v" + Util.getProperty("project.version"));

        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
