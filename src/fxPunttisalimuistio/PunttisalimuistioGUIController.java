package fxPunttisalimuistio;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Luokka muistion käyttöliittymän tapahtumien hoitamiseksi.
 * @author Eetu
 * @version 14.02.2023 Tiedoston synty
 * @version 30.05.2023 Uudistuksia
 */
public class PunttisalimuistioGUIController implements Initializable {
    private String kayttaja = "aku";
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private TextField hakuehto;
    @FXML private Label labelVirhe;
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        // TODO Auto-generated method stub 
    }
    
    
    /**
     * Hakuehto-valikko
     */
    @FXML private void handleHakuehto() {
        String hakukentta = cbKentat.getSelectedText();
        String ehto = hakuehto.getText(); 
        if ( ehto.isEmpty() )
            naytaVirhe(null);
        else
            naytaVirhe("Ei osata vielä hakea " + hakukentta + ": " + ehto);
    }
    
//===========================================================================================    
// Tiedosto
    
    /**
     * Tallenna-painike
     */
    @FXML void handleTallenna() {
        tallenna();
    }
    
    
    /**
     * Avaa-painike
     */
    @FXML void handleAvaa() {
        avaa();
    }
    
    
    /**
     * Tulosta-painike
     */
    @FXML void handleTulosta() {
        TulostusController.tulosta(null);
    }
    
    
    /**
     * Lopeta-painike
     */
    @FXML void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
//===========================================================================================    
// Muokkaa
    
    /**
     * Uusi treeni -painike
     */
    @FXML void handleUusiTreeni() {
        Dialogs.showMessageDialog("Vielä ei osata lisätä treeniä");
    }
    
    
    /**
     * Muokkaa treeniä -painike
     */
    @FXML void handleMuokkaaTreeni() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TreeniDialogView.fxml"), "Treeni", null, "");
    }
    
    
    /**
     * Poista treeni -painike
     */
    @FXML void handlePoistaTreeni() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa treeniä");
    }
    
    
    /**
     * Lisää liike -painike
     */
    @FXML void handleUusiLiike() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä liikettä");
    }
    
    
    /**
     * Muokkaa liikettä -painike
     */
    @FXML void handleMuokkaaLiike() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("LiikeDialogView.fxml"), "Liike", null, "");
    }
    
    
    /**
     * Poista liike -painike
     */
    @FXML void handlePoistaLiike() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa liikettä");
    }
    
//===========================================================================================    
// Apua
    
    /**
     * Apua-painike
     */
    @FXML void handleApua() {
        avustus();
    }
    
    
    /**
     * Tietoa-painike
     */
    @FXML void handleTietoa() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TietoaView.fxml"), "Punttisalimuistio", null, "");
    }
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
    
    /**
     * Aliohjelma ilmestyneen virheilmoituksen näyttämiseen käyttöliittymässä
     * @param virhe virheilmoitus
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
    
    
    /**
     * Aseta ohjelmalle nimi
     * @param title ohjelmalle laitettava nimi
     */
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    
    /**
     * Alustaa punttisalimuisiton lukemalla sen valitun nimisestä kansiosta
     * @param nimi kansio josta kayttajan tiedot luetaan
     */
    protected void lueTiedosto(String nimi) {
        kayttaja = nimi;
        setTitle("Punttisalimuistio - " + kayttaja);
        String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
    }
    
    
    /**
     * Kysytään kansion nimi ja luetaan se
     * @return true jos onnistui, false jos ei
     */
    public boolean avaa() {
        String uusikayttaja = KaynnistysController.kysyKayttaja(null, kayttaja);
        if (uusikayttaja == null) return false;
        lueTiedosto(uusikayttaja);
        return true;
    }
    
    
    /**
     * Tietojen tallennustoiminto
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
     * Näytetään ohjelman dokumentaatio erillisessä selaimessa.
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