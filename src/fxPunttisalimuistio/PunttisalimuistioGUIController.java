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
 * @version 12 Feb 2023
 */
public class PunttisalimuistioGUIController implements Initializable {
    
    @FXML private TextField hakuehto;
    @FXML private Label labelVirhe;
    
    private String kayttajannimi = "Aku Ankka";
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        // TODO
    }
    
    
    @FXML private void handleHakuehto() {
        String ehto = hakuehto.getText();
        if ( ehto.isEmpty() )
            naytaVirhe(null);
        else
            naytaVirhe("Ei osata vielä hakea " + ehto);
    }
    
    
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    @FXML private void handleAvaa() {
        avaa();
    }
    
    
    @FXML private void handleTulosta() {
        TulostusController.tulosta(null);
    }
    
    
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    
    @FXML private void handleUusiTreeni() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TreeniView.fxml"), "Treeni", null, "");
    }
    
    
    @FXML private void handleMuokkaaTreeni() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TreeniView.fxml"), "Treeni", null, "");
    }
    
    
    @FXML private void handlePoistaTreeni() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa treeniä");
    }
    
    
    @FXML private void handleMuokkaaKayttaja() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("KayttajaDialogView.fxml"), "Käyttäjä", null, "");
    }
    
        
    @FXML private void handlePoistaKayttaja() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa käyttäjää");
    }
    
    
    @FXML void handleApua() {
        avustus();
    }
    
    
    @FXML private void handleTietoa() { 
       ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TietoaView.fxml"), "Tietoa", null, "");
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
    
    
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    
    /**
     * Alustaa kerhon lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     */
    protected void lueTiedosto(String nimi) {
        kayttajannimi = nimi;
        setTitle("Punttisalimuistio - " + kayttajannimi);
        String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
    }
    
    
    /**
     * Kysytään tiedoston nimi ja luetaan se
     * @return true jos onnistui, false jos ei
     */
    public boolean avaa() {
        String uusinimi = KaynnistysController.kysyKayttaja(null, kayttajannimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        return true;
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