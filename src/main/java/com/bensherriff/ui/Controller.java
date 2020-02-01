package com.bensherriff.ui;

import com.bensherriff.app.Cipher;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    public Button encryptBtn;
    public Button decryptBtn;
    public TextField shiftKey;
    public TextArea inputText;
    public TextArea outputText;
    public Button hackBtn;

    public void encryptClicked(ActionEvent actionEvent) {
        int key = Math.abs(Integer.parseInt(shiftKey.getText()));

        String result = Cipher.encrypt(inputText.getText(), key);
        outputText.setText(result);
    }

    public void decryptClicked(ActionEvent actionEvent) {
        int key = Math.abs(Integer.parseInt(shiftKey.getText()));

        String result = Cipher.decrypt(inputText.getText(), key);
        outputText.setText(result);
    }

    public void hackClicked(ActionEvent actionEvent) {
        String result = Cipher.hack(inputText.getText());
        outputText.setText(result);
    }
}
