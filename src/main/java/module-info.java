module com.app.mychat {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.app.mychat to javafx.fxml;
    opens com.app.mychat.controller;
    exports com.app.mychat.utils.interfaces;
    exports com.app.mychat;
    exports com.app.mychat.utils.classes.ui;
    exports com.app.mychat.utils.classes.backend;
}