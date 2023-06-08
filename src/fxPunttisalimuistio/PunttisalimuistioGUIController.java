package fxPunttisalimuistio;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
 * |--------------------------------------------------------------------------|
 * | Luokan nimi:   Naytto                              | Avustajat:          |
 * |---------------------------------------------------------------------------
 * | Vastuualueet:                                      |                     | 
 * |                                                    | - Punttisalimuistio | 
 * | - hoitaa kaiken näyttöön tulevan tekstin           | - Treeni            | 
 * | - hoitaa kaiken tiedon pyytämisen käyttäjältä      | - Liike             | 
 * | (- ei tiedä punttisalimuistion yksityiskohtia)     |                     | 
 * |                                                    |                     | 
 * |                                                    |                     | 
 * |                                                    |                     | 
 * |                                                    |                     | 
 * |                                                    |                     | 
 * |                                                    |                     | 
 * |                                                    |                     | 
 * |                                                    |                     | 
 * |                                                    |                     | 
 * |                                                    |                     | 
 * |---------------------------------------------------------------------------
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
    @FXML private ScrollPane panelTreeni;
    @FXML private ListChooser<Treeni> chooserTreenit;
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta(); 
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
        TulostusController tulostusCtrl = TulostusController.tulosta(null); 
        tulostaValitut(tulostusCtrl.getTextArea());
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
        uusiTreeni();
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
        uusiLiike();
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
    
    private Punttisalimuistio   muistio;
    private Treeni              treeniKohdalla;
    private TextArea            areaTreeni = new TextArea();
    
    
    /**
     * Tekee tarvittavat muut alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa treenien tiedot.
     * Alustetaan myös treenilistan kuuntelija 
     */
    protected void alusta() {
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
     * Näyttää listasta valitun treenin tiedot, tilapäisesti yhteen isoon edit-kenttään
     */
    protected void naytaTreeni() {
        treeniKohdalla = chooserTreenit.getSelectedObject();
        if (treeniKohdalla == null) return;
        areaTreeni.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaTreeni)) {
            tulosta(os,treeniKohdalla);
        }
    }
    
    
    /**
     * Hakee treenien tiedot listaan
     * @param tnro treenin numero, joka aktivoidaan haun jälkeen
     */
    protected void hae(int tnro) {
        chooserTreenit.clear();
        int index = 0;
        for (int i = 0; i < muistio.getTreeneja(); i++) {
            Treeni treeni = muistio.annaTreeni(i);
            if (treeni.getTunnusNro() == tnro) index = i;
            chooserTreenit.add(treeni.getPvm(), treeni);
        }
        chooserTreenit.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää treenin
    }
    
    
    /**
     * Luo uuden treenin jota aletaan editoimaan 
     */
    protected void uusiTreeni() {
        Treeni uusi = new Treeni();
        uusi.rekisteroi();
        uusi.taytaTreeni();
        try {
            muistio.lisaa(uusi);
        } catch (SailoException e) {
            Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
            return;
        }
        hae(uusi.getTunnusNro());
    }
    
    
    /**
     * Tekee uuden tyhjän liikkeen editointia varten
     */
    public void uusiLiike() {
        if (treeniKohdalla == null) return;
        Liike lii = new Liike();
        lii.rekisteroi();
        lii.taytaLiike(treeniKohdalla.getTunnusNro());
        muistio.lisaa(lii);
        hae(treeniKohdalla.getTunnusNro());
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
        List<Liike> liikkeet = muistio.annaLiikkeet(treeni);
        for (Liike lii : liikkeet) {
            lii.tulosta(os);
        }
    }
    
    
    /**
     * Tulostaa listassa olevat treenit tekstialueeseen
     * @param text alue johon tulostetaan
     */
    public void tulostaValitut(TextArea text) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(text)) {
            os.println("Tulostetaan kaikki treenit");
            for (int i = 0; i < muistio.getTreeneja(); i++) {
                Treeni treeni = muistio.annaTreeni(i);
                tulosta(os, treeni);
                os.println("\n\n");
            }
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
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
}