package com.example.myphotoshopbeta;

import com.example.myphotoshopbeta.util.MyImageProcessor;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class HelloController {
    public ImageView imageView;
    public BufferedImage copy;
    public boolean flag = false;
    static public File arq = null;
    private Image img;






    public void onAbrirArquivo(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image.ALL", "*.*")
                ,new FileChooser.ExtensionFilter("Image.JPG", "*.jpg")
                ,new FileChooser.ExtensionFilter("Image.JPEG","*.jpeg")
                ,new FileChooser.ExtensionFilter("Image.GIF", "*.gif")
                ,new FileChooser.ExtensionFilter("Image.PNG", "*.png"));

        arq = fc.showOpenDialog(null);

        if(arq!=null)
        {

            img = new Image(arq.toURI().toString());
            imageView.setImage(img);
            imageView.setFitWidth(img.getWidth());
            imageView.setFitHeight(img.getHeight());
            ((Stage)( imageView.getScene().getWindow())).setTitle(arq.getAbsolutePath()+" - MyPhotoShop");
        }
    }


    public void onSalvar(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();

        BufferedImage bimg = SwingFXUtils.fromFXImage(img, null);

        BufferedImage copy = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = copy.createGraphics();
        g2d.setColor(Color.WHITE); // Or what ever fill color you want...
        g2d.fillRect(0, 0, copy.getWidth(), copy.getHeight());
        g2d.drawImage(bimg, 0, 0, null);
        g2d.dispose();

        try
        {
            ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null),"png",arq);
        }
        catch(Exception e)
        {
            ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null),"jpg",arq);
        }

    }





    public void onSalvarComo(ActionEvent actionEvent) throws IOException{

        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".JPG", "*.jpg")
                ,new FileChooser.ExtensionFilter(".JPEG","*.jpeg")
                ,new FileChooser.ExtensionFilter(".GIF", "*.gif")
                ,new FileChooser.ExtensionFilter(".PNG", "*.png"));

        fc.setInitialFileName("NewImage");
        BufferedImage bimg = SwingFXUtils.fromFXImage(img, null);

        arq = fc.showSaveDialog(null);

        copy = new BufferedImage(bimg.getWidth(), bimg.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = copy.createGraphics();
        g2d.setColor(Color.WHITE); // Or what ever fill color you want...
        g2d.fillRect(0, 0, copy.getWidth(), copy.getHeight());
        g2d.drawImage(bimg, 0, 0, null);
        g2d.dispose();


        if(arq!=null){

            if(arq.getAbsolutePath().endsWith("png")){
                ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null),"png",arq);
            }
            else if(arq.getAbsolutePath().endsWith("jpeg")){

                ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), copy),"jpeg",arq);
            }
            else if(arq.getAbsolutePath().endsWith("jpg")){
                ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), copy),"jpg",arq);
            }
            else
                ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null),"gif",arq);
        }


    }

    public void onFechar(ActionEvent actionEvent) throws IOException {
        if(!flag)
        {
            Platform.exit();
        }
        else{

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("aviso-fechar.fxml")));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("atenção");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();

            Platform.exit();
        }



    }

    public void onTonsCinza(ActionEvent actionEvent) {
        imageView.setImage(MyImageProcessor.toGrayScale(imageView.getImage()));
        flag=true;
    }

    public void onPretoBranco(ActionEvent actionEvent) {
        imageView.setImage(MyImageProcessor.toBlackWhite(imageView.getImage()));
        flag=true;
    }

    public void onEspelharH(ActionEvent actionEvent) {
        imageView.setImage(MyImageProcessor.InverterH(imageView.getImage(),true));
        flag=true;
    }

    public void onEspelharV(ActionEvent actionEvent) {
        imageView.setImage(MyImageProcessor.InverterV(imageView.getImage(),true));
        flag=true;
    }

    public void onDilatar(ActionEvent actionEvent) {
        imageView.setImage(MyImageProcessor.Dilatar(imageView.getImage()));
        flag=true;
    }

    public void onErosao(ActionEvent actionEvent) {
        imageView.setImage(MyImageProcessor.Erosao(imageView.getImage()));
        flag=true;
    }



    public void onNegativo(ActionEvent actionEvent) {
        imageView.setImage(MyImageProcessor.negativo(imageView.getImage()));
        flag=true;

    }
    public void onClickar(MouseEvent event) {
        imageView.setImage(MyImageProcessor.desenhar(imageView.getImage(),(int)event.getX(), (int)event.getY(), Color.BLACK));
        flag=true;
    }

    public void onPress(MouseEvent event) {
        imageView.setImage(MyImageProcessor.desenhar(imageView.getImage(),(int)event.getX(), (int)event.getY(), Color.BLACK));
        flag=true;
    }

    public void onAbrirSobre(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("tela-sobre.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sobre");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.showAndWait();

    }

    public void onExpo(ActionEvent actionEvent) {
        imageView.setImage(MyImageProcessor.expo(imageView.getImage()));
        flag=true;
    }

    public void onSuv(ActionEvent actionEvent) {
        imageView.setImage(MyImageProcessor.suav(imageView.getImage()));
        flag=true;
    }

    public void onConv(ActionEvent actionEvent) {
        imageView.setImage(MyImageProcessor.conv(imageView.getImage()));
        flag=true;
    }
}