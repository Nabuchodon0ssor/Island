module com.game.island {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;


    opens com.game.island to javafx.fxml;
    opens config to com.fasterxml.jackson.databind, com.fasterxml.jackson.dataformat.yaml;
    exports com.game.island;
}