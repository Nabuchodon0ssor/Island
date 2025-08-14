module com.game.island {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.game.island to javafx.fxml;
    exports com.game.island;
}