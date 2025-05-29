module com.mycompany.oneshot {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires json;
    requires java.base;

    opens com.mycompany.oneshot to javafx.fxml;
    exports com.mycompany.oneshot;
}
