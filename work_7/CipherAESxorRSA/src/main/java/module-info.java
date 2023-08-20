module com.example.securedocumentexchange {
    requires javafx.controls;
    requires javafx.fxml;
    requires maverick.base;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.cipheraesxorrsa to javafx.fxml;
    exports com.example.cipheraesxorrsa;
    exports com.example.cipheraesxorrsa.controller;
    opens com.example.cipheraesxorrsa.controller to javafx.fxml;
}