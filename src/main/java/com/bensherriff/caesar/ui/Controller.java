package com.bensherriff.caesar.ui;

import com.bensherriff.caesar.app.Cipher;
import com.bensherriff.caesar.app.Timer;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class.getName());

    public Button encryptBtn;
    public Button decryptBtn;
    public TextField shiftKey;
    public TextArea inputText;
    public TextArea outputText;
    public Button hackBtn;

    public void buttonClicked(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String id = btn.getId();
        int key;
        try {
            key = Math.abs(Integer.parseInt(shiftKey.getText()));
        } catch (NumberFormatException ex) {
            LOGGER.error("Invalid key: " + shiftKey.getText());
            shiftKey.setText("0");
            return;
        }
        String result;
        Timer timer = new Timer();
        switch (id) {
            case "encryptBtn":
                timer.start();
                result = Cipher.encrypt(inputText.getText(), key);
                timer.stop();
                LOGGER.info(timer.output());
                break;
            case "decryptBtn":
                timer.start();
                result = Cipher.decrypt(inputText.getText(), key);
                timer.stop();
                LOGGER.info(timer.output());
                break;
            case "hackBtn":
                timer.start();
                result = Cipher.crack(inputText.getText());
                timer.stop();
                LOGGER.info(timer.output());
                break;
            default:
                result = "Invalid button: " + id;
                LOGGER.error(result);
                break;
        }
        outputText.setText(result);
    }
}
