module com.example.lc_management_software {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.logging;

    opens com.example.lc_management_software to javafx.fxml;
    exports com.example.lc_management_software;
    opens com.example.lc_management_software.model_classes to javafx.base;
}