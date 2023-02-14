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
 * @version 14 Feb 2023
 */
public class PunttisalimuistioGUIController implements Initializable {
    
    @FXML private TextField textHakuehto;
    @FXML private Label labelVirhe;
    private String kayttajannimi = "Aku Ankka";
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        // TODO
    }
    
    
    /**
     * Päiväyksien-hakukenttä
     */
    @FXML private void handleHakuehto() {
        String ehto = textHakuehto.getText();
        if ( ehto.isEmpty() )
            naytaVirhe(null);
        else
            naytaVirhe("Ei osata vielä hakea " + ehto);
    }
    
    
    /**
     * Tallenna-painike
     */
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Avaa-painike
     */
    @FXML private void handleAvaa() {
        avaa();
    }
    
    
    /**
     * Tulosta-painike
     */
    @FXML private void handleTulosta() {
        TulostusController.tulosta(null);
    }
    
    
    /**
     * Lopeta-painike
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    
    /**
     * Uusi treeni -painike
     */
    @FXML private void handleUusiTreeni() {
        TreeniController.treeni(null);
    }
    
    
    /**
     * Muokkaa treeniä -painike
     */
    @FXML private void handleMuokkaaTreeni() {
        TreeniController.treeni(null);
    }
    
    
    /**
     * Poista treeni -painike
     */
    @FXML private void handlePoistaTreeni() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa treeniä");
    }
    
    
    /**
     * Muokkaa käyttäjää -painike
     */
    @FXML private void handleMuokkaaKayttaja() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("KayttajaDialogView.fxml"), "Käyttäjä", null, "");
    }
    
    
    /**
     * Poista käyttäjä -painike
     */
    @FXML private void handlePoistaKayttaja() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa käyttäjää");
    }
    
    
    /**
     * Apua-painike
     */
    @FXML void handleApua() {
        avustus();
    }
    
    
    /**
     * Tietoa-painike
     */
    @FXML private void handleTietoa() { 
       ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TietoaView.fxml"), "Tietoa", null, "");
    }
    
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia    
    
    
    /**
     * Virhepalkin toiminta
     * @param virhe Selitys virheestä
     */
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
        ModalController.getStage(textHakuehto).setTitle(title);
    }
    
    
    /**
     * Alustaa muistion lukemalla valitun käyttäjän tiedot tiedostoista
     * @param nimi Käyttäjä josta haetaan muistio tiedot
     */
    protected void lueTiedosto(String nimi) {
        kayttajannimi = nimi;
        setTitle("Punttisalimuistio - " + kayttajannimi);
        String virhe = "Ei osata lukea käyttäjätietoja vielä";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
    }
    
    
    /**
     * Kysytään käyttäjä ja luetaan käyttäjätiedot
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