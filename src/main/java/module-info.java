module com.mycompany.anotheria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.anotheria to javafx.fxml;
    exports com.mycompany.anotheria;
    requires org.apache.poi.poi;
}
