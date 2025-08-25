module com.game.island {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;


    opens com.game.island to javafx.fxml;
    exports com.game.island;
}