/**
 * 
 */
/**
 * 
 */
module GUIProject {
	requires java.desktop;
	requires javafx.controls;
    requires javafx.fxml;
    
    exports hust.soict.dsai.javafx;
    opens hust.soict.dsai.javafx to javafx.fxml;
}