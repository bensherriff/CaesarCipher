package com.bensherriff;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    public Button encryptBtn;
    public Button decryptBtn;
    public Button crackBtn;
    public TextField shiftKey;
    public TextField inputText;
    public TextField outputText;

    public void encryptClicked(ActionEvent actionEvent) {
        int key = Integer.parseInt(shiftKey.getText());

        String result = Cipher.encrypt(inputText.getText(), key);
        outputText.setText(result);
    }

    public void decryptClicked(ActionEvent actionEvent) {
        int key = Integer.parseInt(shiftKey.getText());

        String result = Cipher.decrypt(inputText.getText(), key);
        outputText.setText(result);
    }

    public void crackClicked(ActionEvent actionEvent) {
        String result = Cipher.crack(inputText.getText());
        outputText.setText(result);
    }
}
