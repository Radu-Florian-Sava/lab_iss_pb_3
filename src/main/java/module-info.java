module iss.ubbcluj.ro.interfataisspb3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;

    opens iss.ubbcluj.ro.interfataisspb3 to javafx.fxml;
    exports iss.ubbcluj.ro.interfataisspb3;
}