package com.example.myphotoshopbeta.util;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class MyImageProcessor {
    static public Image toGrayScale(Image img){
        int pixel[]={0,0,0,0};
        int cinza;
        BufferedImage bimg = SwingFXUtils.fromFXImage(img, null);
        WritableRaster raster= bimg.getRaster();
        for(int x=0; x<bimg.getWidth(); x++)
                for(int y=0; y<bimg.getHeight();y++){
                    raster.getPixel(x,y,pixel);
                    cinza =(int)(pixel[0]*0.3+pixel[1]*0.59+pixel[3]*0.11);
                    pixel[0] = pixel[1] = pixel[2] = cinza;
                    raster.setPixel(x,y,pixel);
                }
        return SwingFXUtils.toFXImage(bimg,null);
    }

    static public Image toBlackWhite(Image img){
        int pixel[]={0,0,0,0};
        int cinza;
        BufferedImage bimg = SwingFXUtils.fromFXImage(img, null);
        WritableRaster raster= bimg.getRaster();
        for(int x=0; x<bimg.getWidth(); x++)
            for(int y=0; y<bimg.getHeight();y++){
                raster.getPixel(x,y,pixel);
                cinza =(int)(pixel[0]*0.3+pixel[1]*0.59+pixel[3]*0.11);
                pixel[0] = pixel[1] = pixel[2] = cinza;

                if((pixel[0]+pixel[1]+pixel[2])/3>100){
                    pixel[0] = pixel[1] = pixel[2] = 0;
                }
                else{
                    pixel[0] = pixel[1] = pixel[2] = 255;
                }
                raster.setPixel(x,y,pixel);
            }
        return SwingFXUtils.toFXImage(bimg,null);
    }
    static public Image Dilatar(Image img){
        ImagePlus imgPlus = new ImagePlus();
        BufferedImage bimg = SwingFXUtils.fromFXImage(img, null);
        imgPlus.setImage(bimg);
        ImageProcessor ip = imgPlus.getProcessor();
        ip.dilate();
        return SwingFXUtils.toFXImage(imgPlus.getBufferedImage(),null);
    }
    static  public Image Erosao(Image img){
        ImagePlus imgPlus = new ImagePlus();
        BufferedImage bimg = SwingFXUtils.fromFXImage(img, null);
        imgPlus.setImage(bimg);
        ImageProcessor ip = imgPlus.getProcessor();
        ip.erode();
        return SwingFXUtils.toFXImage(imgPlus.getBufferedImage(),null);
    }
    static public Image InverterH(Image img, boolean Horizontal){
        BufferedImage bimg = SwingFXUtils.fromFXImage(img, null);
        int [] pixel = {0,0,0,0};
        int width = bimg.getWidth();
        int height = bimg.getHeight();
        BufferedImage bimgAux = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bimg.getRaster();
        WritableRaster write = bimgAux.getRaster();
        for(int i = 0; i < height;i++ ){
            for(int j = 0; j < width; j++) {
                raster.getPixel(j,i,pixel);
                if(Horizontal){
                    write.setPixel((width-1)-j,i,pixel);
                }
                else{
                    write.setPixel(j,(height-1)-i,pixel);
                }
            }
        }
        return SwingFXUtils.toFXImage(bimgAux,null);
    }
    static public Image InverterV(Image img, boolean vertical){
        BufferedImage bimg = SwingFXUtils.fromFXImage(img, null);
        int [] pixel = {0,0,0,0};
        int width = bimg.getWidth();
        int height = bimg.getHeight();
        BufferedImage bimgAux = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = bimg.getRaster();
        WritableRaster write = bimgAux.getRaster();
        for(int i = 0; i < height;i++ ){
            for(int j = 0; j < width; j++) {
                raster.getPixel(j,i,pixel);
                if(vertical){
                    write.setPixel(j,(height-1)-i,pixel);
                }
                else{
                    write.setPixel((width-1)-j,i,pixel);
                }
            }
        }
        return SwingFXUtils.toFXImage(bimgAux,null);
    }

    public static Image negativo(Image img)
    {
        BufferedImage bimagem;
        bimagem = SwingFXUtils.fromFXImage(img, null);
        WritableRaster raster = bimagem.getRaster();
        int[] pixel={0,0,0,0};
        for (int i = 0; i < img.getHeight(); i++)
        {

            for (int j = 0; j < img.getWidth(); j++)
            {
                raster.getPixel(j, i, pixel);
                pixel[0] = 255 - pixel[0];
                pixel[1] = 255 - pixel[1];
                pixel[2] = 255 - pixel[2];
                raster.setPixel(j, i, pixel);
            }
        }
        return SwingFXUtils.toFXImage(bimagem, null);
    }

    public static Image expo(Image img) {
        BufferedImage bimagem;
        bimagem = SwingFXUtils.fromFXImage(img, null);
        ImagePlus imgplus=new ImagePlus();

        imgplus.setImage(bimagem);
        ImageProcessor ipr=imgplus.getProcessor();
        ipr.exp();

        return SwingFXUtils.toFXImage(imgplus.getBufferedImage(), null);
    }

    public static Image suav(Image img) {
        BufferedImage bimagem;
        bimagem = SwingFXUtils.fromFXImage(img, null);
        ImagePlus imgplus=new ImagePlus();

        imgplus.setImage(bimagem);
        ImageProcessor ipr=imgplus.getProcessor();
        ipr.smooth();

        return SwingFXUtils.toFXImage(imgplus.getBufferedImage(), null);
    }

    public static Image conv(Image img) {
        BufferedImage bimagem;
        bimagem = SwingFXUtils.fromFXImage(img, null);
        ImagePlus imgplus=new ImagePlus();

        imgplus.setImage(bimagem);
        ImageProcessor ipr=imgplus.getProcessor();
        ipr.sharpen();

        return SwingFXUtils.toFXImage(imgplus.getBufferedImage(), null);
    }


    public static Image desenhar(Image img, int x, int y, Color cor){
        BufferedImage bimagem;
        bimagem = SwingFXUtils.fromFXImage(img, null);
        WritableRaster raster = bimagem.getRaster();
        int[] pixel={0,0,0,0};
        raster.getPixel(x, y, pixel);
        pixel[0] = cor.getRed();
        pixel[1] = cor.getGreen();
        pixel[2] = cor.getBlue();
        raster.setPixel(x, y, pixel);

        raster.getPixel(x-1, y+1, pixel);
        pixel[0] = cor.getRed();
        pixel[1] = cor.getGreen();
        pixel[2] = cor.getBlue();
        raster.setPixel(x-1, y+1, pixel);

        raster.getPixel(x, y+1, pixel);
        pixel[0] = cor.getRed();
        pixel[1] = cor.getGreen();
        pixel[2] = cor.getBlue();
        raster.setPixel(x, y+1, pixel);

        raster.getPixel(x+1, y+1, pixel);
        pixel[0] = cor.getRed();
        pixel[1] = cor.getGreen();
        pixel[2] = cor.getBlue();
        raster.setPixel(x+1, y+1, pixel);

        raster.getPixel(x-1, y, pixel);
        pixel[0] = cor.getRed();
        pixel[1] = cor.getGreen();
        pixel[2] = cor.getBlue();
        raster.setPixel(x-1, y, pixel);

        raster.getPixel(x+1, y, pixel);
        pixel[0] = cor.getRed();
        pixel[1] = cor.getGreen();
        pixel[2] = cor.getBlue();
        raster.setPixel(x+1, y, pixel);

        raster.getPixel(x-1, y-1, pixel);
        pixel[0] = cor.getRed();
        pixel[1] = cor.getGreen();
        pixel[2] = cor.getBlue();
        raster.setPixel(x-1, y-1, pixel);

        raster.getPixel(x-1, y+1, pixel);
        pixel[0] = cor.getRed();
        pixel[1] = cor.getGreen();
        pixel[2] = cor.getBlue();
        raster.setPixel(x, y-1, pixel);

        raster.getPixel(x-1, y+1, pixel);
        pixel[0] = cor.getRed();
        pixel[1] = cor.getGreen();
        pixel[2] = cor.getBlue();
        raster.setPixel(x+1, y-1, pixel);
        return SwingFXUtils.toFXImage(bimagem, null);
    }
}
