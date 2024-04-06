module leonardos{
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens authentication to javafx.fxml;
    opens authentication.controller to  javafx.fxml;
    opens authentication.view to javafx.fxml;
    opens idol to javafx.fxml;
    opens idol.controller to javafx.fxml;
    opens idol.view to javafx.fxml;
    opens fan.view to javafx.fxml;
    opens fan.view.cards to javafx.fxml;
    opens fan.view.popup to javafx.fxml;

    exports authentication;
    exports authentication.view;
    exports authentication.controller;
    exports idol;
    exports idol.view;
    exports idol.controller;
    exports fan.controller;
    exports fan.view;
    exports fan.view.cards;
    exports fan.view.popup;
}