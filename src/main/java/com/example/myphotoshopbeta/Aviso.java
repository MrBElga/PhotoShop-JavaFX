package com.example.myphotoshopbeta;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Aviso {

    public void onSalvarSair(ActionEvent actionEvent){
        Platform.exit();
    }



    public void onSalvarNẫo(ActionEvent actionEvent) {
        Platform.exit();
    }
}
