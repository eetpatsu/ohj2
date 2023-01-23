package fxPunttisaliMuistio;

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
public class PunttisaliMuistioMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("PunttisaliMuistioGUIView.fxml"));
            final Pane root = ldr.load();
            //final PunttisaliMuistioGUIController punttisalimuistioCtrl = (PunttisaliMuistioGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("punttisalimuistio.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("PunttisaliMuistio");
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