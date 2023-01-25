package fxPunttisalimuistio;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author Eetu
 * @version 23.1.2023
 *
 */
public class PunttisalimuistioMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("PunttisalimuistioGUIView.fxml"));
            final Pane root = ldr.load();
            //final PunttisalimuistioGUIController punttisalimuistioCtrl = (PunttisalimuistioGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("punttisalimuistio.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Punttisalimuistio");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei kaytossa
     */
    public static void main(String[] args) {
        launch(args);
    }
}