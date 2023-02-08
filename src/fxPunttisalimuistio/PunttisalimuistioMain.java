package fxPunttisalimuistio;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * Pääohjelma Punttisalimuistio-ohjelman käynnistämiseksi
 * @author Eetu
 * @version 23.1.2023
 */
public class PunttisalimuistioMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("PunttisalimuistioGUIView.fxml"));
            final Pane root = (Pane)ldr.load();
            final PunttisalimuistioGUIController punttisalimuistioCtrl = (PunttisalimuistioGUIController)ldr.getController();
            
            final Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("punttisalimuistio.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Punttisalimuistio");
            
            primaryStage.setOnCloseRequest((event) -> {
                if ( !punttisalimuistioCtrl.voikoSulkea() ) event.consume();
            });
            
            primaryStage.show();
            if ( !punttisalimuistioCtrl.avaa() ) Platform.exit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Käynnistetään käyttöliittymä
     * @param args komentorivin parametrit
     */
    public static void main(String[] args) {
        launch(args);
    }
}