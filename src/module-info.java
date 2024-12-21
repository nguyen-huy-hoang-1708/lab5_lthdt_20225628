module AimsProject {
    requires java.desktop; // For AWT and Swing
    requires javafx.controls; // For JavaFX UI controls
    requires javafx.fxml; // For FXML files
    requires javafx.graphics; // For JavaFX graphics
    requires javafx.swing; // For using JFXPanel

    opens hust.soict.dsai.aims.screen.controller to javafx.fxml, javafx.graphics; // Reflective access for FXML loader
    opens hust.soict.dsai.aims.screen.view to javafx.fxml; // If needed for FXML files in the "view" package

    exports hust.soict.dsai.aims.media; // Continue exporting media-related packages if needed
}
