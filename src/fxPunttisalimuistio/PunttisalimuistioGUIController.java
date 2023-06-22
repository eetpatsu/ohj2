package fxPunttisalimuistio;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import punttisalimuistio.Liike;
import punttisalimuistio.Punttisalimuistio;
import punttisalimuistio.SailoException;
import punttisalimuistio.Treeni;

/**
 * Avustajat: Punttisalimuistio, Treeni, Liike
 * Vastuualueet: Hoitaa kaiken näyttöön tulevan tekstin
 * ja kaiken tiedon pyytämisen käyttäjältä.
 * Ei tiedä punttisalimuistion yksityiskohtia.
 * 
 * Luokka muistion pääkäyttöliittymän tapahtumien hoitamiseen.
 * @author Eetu
 * @version 0.3, 14.02.2023 Tiedoston synty
 * @version 0.4, 30.05.2023 Uudistuksia
 * @version 0.5, 09.06.2023 Treenit ja Liikkeet
 * @version 0.6, 14.06.2023 Tiedostonhallinta
 * @version 0.7.1, 22.06.2023 TreeniDialogController
 */
public class PunttisalimuistioGUIController implements Initializable {    
    @FXML private ComboBoxChooser<String> cbKentat;         // Hakuehto-valikko
    @FXML private TextField hakuehto;                       // Hakuehto-kenttä
    @FXML private Label labelVirhe;                         // Virheilmoitus-kenttä
    @FXML private ScrollPane panelTreeni;                   // Treenin tiedot -paneeli
    @FXML private ListChooser<Treeni> chooserTreenit;       // Treenit-lista
    
    
    /**
     * Kontrollerin alustus
     */
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta(); 
    }
    
    
    /**
     * Hakuehto-valikko
     */
    @FXML private void handleHakuehto() {
        if (treeniKohdalla != null) {
            hae(treeniKohdalla.getTunnusNro());
        }
    }
    
//===========================================================================================    
// Tiedosto-menu
    
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
        TulostusController tulostusCtrl = TulostusController.tulosta(null); 
        tulostaValitut(tulostusCtrl.getTextArea());
    }
    
    
    /**
     * Lopeta-painike
     */
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
//===========================================================================================    
// Muokkaa-menu
    
    /**
     * Uusi treeni -painike
     */
    @FXML private void handleUusiTreeni() {
        uusiTreeni();
    }
    
    
    /**
     * Muokkaa treeniä -painike
     */
    @FXML private void handleMuokkaaTreeni() {
        muokkaa();
    }
    
    
    /**
     * Poista treeni -painike
     */
    @FXML private void handlePoistaTreeni() {
        Dialogs.showMessageDialog("Vielä ei osata poistaa treeniä");
    }
    
    
    /**
     * Lisää liike -painike
     */
    @FXML private void handleUusiLiike() {
        uusiLiike();
    }
    
    
    /**
     * Muokkaa liikettä -painike
     */
    @FXML private void handleMuokkaaLiike() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("LiikeDialogView.fxml"), "Liike", null, "");
    }
    
    
    /**
     * Poista liike -painike
     */
    @FXML private void handlePoistaLiike() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa liikettä");
    }
    
//===========================================================================================    
// Apua-menu
    
    /**
     * Apua-painike
     */
    @FXML private void handleApua() {
        avustus();
    }
    
    
    /**
     * Tietoa-painike
     */
    @FXML private void handleTietoa() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TietoaView.fxml"), "Punttisalimuistio", null, "");
    }
    
//===========================================================================================    
// Tästä eteenpäin ei käyttöliittymään suoraan liittyvää koodia
//=========================================================================================== 
    
    private String              kayttaja = "aku";
    private Punttisalimuistio   muistio;                        // Tynkä Punttisalimuistio-olioviite
    private Treeni              treeniKohdalla;                 // Tynkä Treeni-olioviite
    private TextArea            areaTreeni = new TextArea();    // Tynkä TextArea-olioviite
    
    
    /**
     * Tekee tarvittavat muut alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa treenien tiedot.
     * Alustetaan myös treenilistan kuuntelija 
     */
    private void alusta() {
        panelTreeni.setContent(areaTreeni);
        areaTreeni.setFont(new Font("Courier New", 12));
        panelTreeni.setFitToHeight(true);
        chooserTreenit.clear();
        chooserTreenit.addSelectionListener(e -> naytaTreeni());
    }
    
    
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
     * Aseta ohjelmaikkunalle nimi
     * @param title
     */
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    
    /**
     * Alustaa punttisalimuisiton lukemalla sen valitun nimisestä hakemistosta
     * @param nimi hakemisto josta käyttajän tiedot luetaan
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    protected String lueTiedosto(String nimi) {
        this.kayttaja = nimi;
        setTitle("Punttisalimuistio - " + this.kayttaja);
        try {
            muistio.lueTiedostosta(nimi);
            hae(0);
            return null;
        } catch (SailoException ex) {
            hae(0);
            if (ex.getMessage() != null)
                Dialogs.showMessageDialog(ex.getMessage());
            return ex.getMessage();
        }
    }
    
    
    /**
     * Kysytään hakemiston nimi ja luetaan se
     * @return true jos onnistui, false jos ei
     */
    public boolean avaa() {
        String uusikayttaja = KaynnistysController.kysyKayttaja(null, this.kayttaja);
        if (uusikayttaja == null) return false;
        lueTiedosto(uusikayttaja);
        return true;
    }
    
    
    /**
     * Tietojen tallennustoiminto
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    private String tallenna() {
        try {
            muistio.talleta();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
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
     * Näyttää listasta valitun treenin tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    private void naytaTreeni() {
        treeniKohdalla = chooserTreenit.getSelectedObject();
        if (treeniKohdalla == null) {
            areaTreeni.clear();
            return;
        }
        areaTreeni.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaTreeni)) {
            tulosta(os, treeniKohdalla);
        }
    }
    
    
    /**
     * Hakee treenien tiedot listaan
     * @param treNro treeni joka valitaan haun jälkeen
     */
    private void hae(int treNro) {
        int indeksi = cbKentat.getSelectionModel().getSelectedIndex();
        String ehto = hakuehto.getText(); 
        if (indeksi > 0 || ehto.length() > 0)
            naytaVirhe(String.format("Ei osata hakea (kenttä: %d, ehto: %s)", indeksi, ehto));
        else
            naytaVirhe(null);
        chooserTreenit.clear();
        int index = 0;
        Collection<Treeni> treenit;
        try {
            treenit = muistio.etsi(ehto, indeksi);
            int i = 0;
            for (Treeni treeni:treenit) {
                if (treeni.getTunnusNro() == treNro) index = i;
                chooserTreenit.add(treeni.getPvm(), treeni);
                i++;
            }
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Treenin hakemisessa ongelmia! " + ex.getMessage());
        }
        chooserTreenit.setSelectedIndex(index);
    }
    
    
    /**
     * Luo uuden treenin jota aletaan editoimaan 
     */
    private void uusiTreeni() {
        Treeni uusi = new Treeni();
        uusi.rekisteroi();
        uusi.taytaTreeni();
        muistio.lisaa(uusi);
        hae(uusi.getTunnusNro());
    }
    
    
    /**
     * Tekee uuden tyhjän liikkeen editointia varten
     */
    public void uusiLiike() {
        if (treeniKohdalla == null)
            return;
        Liike uusi = new Liike();
        uusi.rekisteroi();
        uusi.taytaLiike(treeniKohdalla.getTunnusNro());
        try {
            muistio.lisaa(uusi);
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Ongelmia lisäämisessä! " + ex.getMessage());
        }
        hae(treeniKohdalla.getTunnusNro());
    }
    
    
    /**
     * Avaa valitun treenin muokkausdialogissa
     */
    private void muokkaa() {
        ModalController.showModal(PunttisalimuistioGUIController.class.getResource("TreeniDialogView.fxml"), "Treeni", null, treeniKohdalla);
    }
    
    
    /**
     * Aseta punttisalimuistio.
     * @param muistio Punttisalimuistio jota käytetään tässä käyttöliittymässä
     */
    public void setMuistio(Punttisalimuistio muistio) {
        this.muistio = muistio;
        naytaTreeni();
    }
    
    
    /**
     * Tulostaa treenin tiedot
     * @param os tietovirta johon tulostetaan
     * @param treeni tulostettava jäsen
     */
    public void tulosta(PrintStream os, final Treeni treeni) {
        os.println("----------------------------------------------");
        treeni.tulosta(os);
        os.println("----------------------------------------------");
        try {
            List<Liike> liikkeet = muistio.annaLiikkeet(treeni);
            for (Liike lii : liikkeet) {
                lii.tulosta(os);
            }
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Liikkeiden hakemisessa ongelmia! " + ex.getMessage());
        }
    }
    
    
    /**
     * Tulostaa listassa olevat treenit tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan kaikki treenit");
            Collection<Treeni> treenit = muistio.etsi("", -1); 
            for (Treeni treeni:treenit) { 
                tulosta(os, treeni);
                os.println("\n\n");
            }
        } catch (SailoException ex) { 
            Dialogs.showMessageDialog("Jäsenen hakemisessa ongelmia! " + ex.getMessage()); 
        }
    }
    
    
    /**
     * Näytetään ohjelman dokumentaatio erillisessä selaimessa.
     */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2023k/ht/eetpatsu");
            desktop.browse(uri);
        } catch (URISyntaxException ex) {
            return;
        } catch (IOException ex) {
            return;
        }
    }
}