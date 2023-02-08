package fxPunttisalimuistio;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Luokka muistion käyttöliittymän tapahtumien hoitamiseksi.
 * @author Eetu
 * @version 23.1.2023
 */
public class PunttisalimuistioGUIController implements Initializable {
    
    @FXML private TextField hakuehto;
    @FXML private Label labelVirhe;
    
    
    @FXML void handleApua() {
        avustus();
    }
    
    
    @FXML void handleHakuehto() {
        String ehto = hakuehto.getText();
        if ( ehto.isEmpty() )
            naytaVirhe(null);
        else
            naytaVirhe("Ei osata vielä hakea " + ehto);
    }
    
    
    @FXML void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    
    @FXML void handleMuokkaaKayttaja() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("KayttajaDialogView.fxml"), "Käyttäjä", null, "");
    }
    
    
    @FXML void handleMuokkaaTreeni() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TreeniView.fxml"), "Treeni", null, "");
    }
    
    
    @FXML void handlePoistaKayttaja() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa käyttäjää");
    }
    
    
    @FXML void handlePoistaTreeni() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa treeniä");
    }
    
    
    @FXML void handleTallenna() {
        tallenna();
    }
    
    
    @FXML void handleTietoa() { 
       // Dialogs.showMessageDialog("Ei osata vielä tietoa");
       ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TietoaView.fxml"), "Tietoa", null, "");
    }
    
    
    @FXML void handleTulosta() {
        TulostusController.tulosta(null);
    }
    
    
    @FXML void handleUusiTreeni() {
        Dialogs.showMessageDialog("Vielä ei osata lisätä treeniä");
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }
    
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia    
    
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    /**
     * Tietojen tallennus
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
    }
    
    
    /**
     * Tarkistetaan onko tallennus tehty
     * @return true jos saa sulkea sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }
    
    
    /**
     * Näytetään ohjelman suunnitelma erillisessä selaimessa.
    */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2023k/ht/eetpatsu");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
}