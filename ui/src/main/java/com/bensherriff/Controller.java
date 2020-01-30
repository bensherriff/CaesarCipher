package com.bensherriff;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    public Button encryptBtn;
    public Button decryptBtn;
    public Button crackBtn;
    public TextField text;
    public TextField shiftKey;

    public void encryptClicked(ActionEvent actionEvent) {
        int key = Integer.parseInt(shiftKey.getText());

        String result = Cipher.encrypt(text.getText(), key);
        text.setText(result);
    }

    public void decryptClicked(ActionEvent actionEvent) {
        int key = Integer.parseInt(shiftKey.getText());

        String result = Cipher.decrypt(text.getText(), key);
        text.setText(result);
    }

    public void crackClicked(ActionEvent actionEvent) {
        String result = Cipher.crack(text.getText());
        text.setText(result);
    }
}
