module com.example.myphotoshopbeta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    requires ij;


    opens com.example.myphotoshopbeta to javafx.fxml;
    exports com.example.myphotoshopbeta;
}