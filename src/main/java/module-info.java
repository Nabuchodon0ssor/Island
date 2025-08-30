module com.game.island {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;


    opens com.game.island.ui to javafx.fxml;

    opens com.game.island.config to com.fasterxml.jackson.databind, com.fasterxml.jackson.dataformat.yaml;

    exports com.game.island;
    exports com.game.island.entities;
    exports com.game.island.entities.interfaces;
}