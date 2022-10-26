package com.example.myphotoshopbeta;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class TelsobreController {
    public void onCloseAbout(ActionEvent actionEvent) {
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }
}
